import { useState, useEffect } from 'react'

const API_BASE = 'http://localhost:8080/api'

function AppointmentsPage() {
  const [appointments, setAppointments] = useState([])
  const [doctors, setDoctors] = useState([])
  const [bookMessage, setBookMessage] = useState('')
  const [isBookError, setIsBookError] = useState(false)

  const [patientName, setPatientName] = useState('')
  const [patientId, setPatientId] = useState('')
  const [selectedDoctorId, setSelectedDoctorId] = useState('')
  const [appointmentDate, setAppointmentDate] = useState('')

  useEffect(() => {
    fetchAppointments()
    fetchDoctors()
  }, [])

  function fetchAppointments() {
    fetch(API_BASE + '/appointments')
      .then(function (res) {
        return res.json()
      })
      .then(function (data) {
        setAppointments(data)
      })
      .catch(function () {
        console.error('Failed to load appointments.')
      })
  }

  function fetchDoctors() {
    fetch(API_BASE + '/doctors')
      .then(function (res) {
        return res.json()
      })
      .then(function (data) {
        setDoctors(data)
      })
      .catch(function () {
        console.error('Failed to load doctors.')
      })
  }

  function handleBookAppointment(e) {
    e.preventDefault()

    const newAppointment = {
      patientName: patientName,
      patientId: parseInt(patientId),
      doctorId: parseInt(selectedDoctorId),
      appointmentDate: appointmentDate
    }

    fetch(API_BASE + '/appointments', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newAppointment)
    })
      .then(function (res) {
        if (res.status === 201) {
          return res.json().then(function () {
            setIsBookError(false)
            setBookMessage('Appointment booked successfully!')
            setPatientName('')
            setPatientId('')
            setSelectedDoctorId('')
            setAppointmentDate('')
            fetchAppointments()
          })
        } else {
          return res.text().then(function (errMsg) {
            setIsBookError(true)
            setBookMessage(errMsg || 'Failed to book appointment.')
          })
        }
      })
      .catch(function () {
        setIsBookError(true)
        setBookMessage('Failed to book appointment.')
      })
  }

  function handleCancelAppointment(id) {
    fetch(API_BASE + '/appointments/' + id, {
      method: 'DELETE'
    })
      .then(function (res) {
        if (res.status === 204) {
          setAppointments(function (prev) {
            return prev.filter(function (appt) {
              return appt.id !== id
            })
          })
        } else {
          alert('Could not cancel appointment.')
        }
      })
      .catch(function () {
        alert('Error cancelling appointment.')
      })
  }

  function getDoctorName(doctorId) {
    let found = null
    for (let i = 0; i < doctors.length; i++) {
      if (doctors[i].id === doctorId) {
        found = doctors[i]
      }
    }
    if (found !== null) {
      return found.fullName
    } else {
      return 'Unknown'
    }
  }

  return (
    <div>
      <h1 className="text-2xl font-bold text-blue-600 mb-6">Appointments</h1>

      <div className="bg-white rounded-lg shadow p-6 mb-6">
        <h2 className="text-lg font-semibold text-gray-700 mb-4">
          Book an Appointment
        </h2>
        <form onSubmit={handleBookAppointment} className="space-y-4">

          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-600">Patient Name</label>
            <input
              type="text"
              value={patientName}
              onChange={function (e) { setPatientName(e.target.value) }}
              required
              placeholder=""
              className="border border-gray-300 rounded px-3 py-2 text-sm focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-600">Patient ID</label>
            <input
              type="number"
              value={patientId}
              onChange={function (e) { setPatientId(e.target.value) }}
              required
              placeholder="e.g. 10045"
              className="border border-gray-300 rounded px-3 py-2 text-sm focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-600">Doctor</label>
            <select
              value={selectedDoctorId}
              onChange={function (e) { setSelectedDoctorId(e.target.value) }}
              required
              className="border border-gray-300 rounded px-3 py-2 text-sm focus:outline-none focus:border-blue-500"
            >
              <option value="">-- Select a Doctor --</option>
              {doctors.map(function (doctor) {
                return (
                  <option key={doctor.id} value={doctor.id}>
                    {doctor.fullName} — {doctor.specialization}
                  </option>
                )
              })}
            </select>
          </div>

          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-600">Appointment Date</label>
            <input
              type="date"
              value={appointmentDate}
              onChange={function (e) { setAppointmentDate(e.target.value) }}
              required
              className="border border-gray-300 rounded px-3 py-2 text-sm focus:outline-none focus:border-blue-500"
            />
          </div>

          <button
            type="submit"
            className="bg-blue-600 text-white px-5 py-2 rounded font-medium hover:bg-blue-700 transition-colors text-sm"
          >
            Book Appointment
          </button>

        </form>

        {bookMessage && (
          <p className={`mt-4 text-sm px-4 py-2 rounded font-medium ${isBookError ? 'bg-red-50 text-red-700' : 'bg-green-50 text-green-700'}`}>
            {bookMessage}
          </p>
        )}
      </div>

      <div className="bg-white rounded-lg shadow p-6">
        <h2 className="text-lg font-semibold text-gray-700 mb-4">
          All Appointments
        </h2>
        {appointments.length === 0 ? (
          <p className="text-gray-400 italic text-sm">No appointments found.</p>
        ) : (
          <table className="w-full text-sm">
            <thead>
              <tr className="bg-blue-50 text-blue-600 text-left">
                <th className="px-4 py-3 font-semibold">ID</th>
                <th className="px-4 py-3 font-semibold">Patient Name</th>
                <th className="px-4 py-3 font-semibold">Patient ID</th>
                <th className="px-4 py-3 font-semibold">Doctor</th>
                <th className="px-4 py-3 font-semibold">Date</th>
                <th className="px-4 py-3 font-semibold">Action</th>
              </tr>
            </thead>
            <tbody>
              {appointments.map(function (appt) {
                return (
                  <tr key={appt.id} className="border-b border-gray-100 hover:bg-gray-50">
                    <td className="px-4 py-3 text-gray-500">{appt.id}</td>
                    <td className="px-4 py-3 text-gray-800 font-medium">{appt.patientName}</td>
                    <td className="px-4 py-3 text-gray-600">{appt.patientId}</td>
                    <td className="px-4 py-3 text-gray-600">{getDoctorName(appt.doctorId)}</td>
                    <td className="px-4 py-3 text-gray-600">{appt.appointmentDate}</td>
                    <td className="px-4 py-3">
                      <button
                        onClick={function () { handleCancelAppointment(appt.id) }}
                        className="bg-red-500 text-white px-3 py-1 rounded text-xs font-medium hover:bg-red-600 transition-colors"
                      >
                        Cancel
                      </button>
                    </td>
                  </tr>
                )
              })}
            </tbody>
          </table>
        )}
      </div>
    </div>
  )
}

export default AppointmentsPage
