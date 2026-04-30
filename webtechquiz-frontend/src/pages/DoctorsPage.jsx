import { useState, useEffect } from 'react'

const API_BASE = 'http://localhost:8080/api'

function DoctorsPage() {
  const [doctors, setDoctors] = useState([])
  const [message, setMessage] = useState('')
  const [isError, setIsError] = useState(false)

  const [fullName, setFullName] = useState('')
  const [specialization, setSpecialization] = useState('')
  const [department, setDepartment] = useState('')
  const [available, setAvailable] = useState(true)

  useEffect(() => {
    fetchDoctors()
  }, [])

  function fetchDoctors() {
    fetch(API_BASE + '/doctors')
      .then(function (res) {
        return res.json()
      })
      .then(function (data) {
        setDoctors(data)
      })
      .catch(function () {
        setIsError(true)
        setMessage('Failed to load doctors.')
      })
  }

  function handleAddDoctor(e) {
    e.preventDefault()

    const newDoctor = {
      fullName: fullName,
      specialization: specialization,
      department: department,
      available: available
    }

    fetch(API_BASE + '/doctors', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newDoctor)
    })
      .then(function (res) {
        if (res.status === 201) {
          return res.json()
        } else {
          throw new Error('Failed to add doctor.')
        }
      })
      .then(function () {
        setIsError(false)
        setMessage('Doctor added successfully!')
        setFullName('')
        setSpecialization('')
        setDepartment('')
        setAvailable(true)
        fetchDoctors()
      })
      .catch(function () {
        setIsError(true)
        setMessage('Failed to add doctor.')
      })
  }

  return (
    <div>
      <h1 className="text-2xl font-bold text-blue-600 mb-6">Doctors</h1>

      <div className="bg-white rounded-lg shadow p-6 mb-6">
        <h2 className="text-lg font-semibold text-gray-700 mb-4">
          Add New Doctor
        </h2>
        <form onSubmit={handleAddDoctor} className="space-y-4">

          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-600">Full Name</label>
            <input
              type="text"
              value={fullName}
              onChange={function (e) { setFullName(e.target.value) }}
              required
              placeholder=""
              className="border border-gray-300 rounded px-3 py-2 text-sm focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-600">Specialization</label>
            <input
              type="text"
              value={specialization}
              onChange={function (e) { setSpecialization(e.target.value) }}
              required
              placeholder="e.g. Dermatologist"
              className="border border-gray-300 rounded px-3 py-2 text-sm focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-600">Department</label>
            <input
              type="text"
              value={department}
              onChange={function (e) { setDepartment(e.target.value) }}
              required
              placeholder="e.g. ICU"
              className="border border-gray-300 rounded px-3 py-2 text-sm focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-600">Available</label>
            <select
              value={available}
              onChange={function (e) { setAvailable(e.target.value === 'true') }}
              className="border border-gray-300 rounded px-3 py-2 text-sm focus:outline-none focus:border-blue-500"
            >
              <option value="true">Yes</option>
              <option value="false">No</option>
            </select>
          </div>

          <button
            type="submit"
            className="bg-blue-600 text-white px-5 py-2 rounded font-medium hover:bg-blue-700 transition-colors text-sm"
          >
            Add Doctor
          </button>

        </form>

        {message && (
          <p className={`mt-4 text-sm px-4 py-2 rounded font-medium ${isError ? 'bg-red-50 text-red-700' : 'bg-green-50 text-green-700'}`}>
            {message}
          </p>
        )}
      </div>

      <div className="bg-white rounded-lg shadow p-6">
        <h2 className="text-lg font-semibold text-gray-700 mb-4">
          All Doctors
        </h2>
        {doctors.length === 0 ? (
          <p className="text-gray-400 italic text-sm">No doctors found.</p>
        ) : (
          <table className="w-full text-sm">
            <thead>
              <tr className="bg-blue-50 text-blue-600 text-left">
                <th className="px-4 py-3 font-semibold">ID</th>
                <th className="px-4 py-3 font-semibold">Full Name</th>
                <th className="px-4 py-3 font-semibold">Specialization</th>
                <th className="px-4 py-3 font-semibold">Department</th>
                <th className="px-4 py-3 font-semibold">Available</th>
              </tr>
            </thead>
            <tbody>
              {doctors.map(function (doctor) {
                return (
                  <tr key={doctor.id} className="border-b border-gray-100 hover:bg-gray-50">
                    <td className="px-4 py-3 text-gray-500">{doctor.id}</td>
                    <td className="px-4 py-3 text-gray-800 font-medium">{doctor.fullName}</td>
                    <td className="px-4 py-3 text-gray-600">{doctor.specialization}</td>
                    <td className="px-4 py-3 text-gray-600">{doctor.department}</td>
                    <td className="px-4 py-3">
                      {doctor.available ? (
                        <span className="bg-green-100 text-green-700 px-2 py-1 rounded-full text-xs font-semibold">Yes</span>
                      ) : (
                        <span className="bg-red-100 text-red-700 px-2 py-1 rounded-full text-xs font-semibold">No</span>
                      )}
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

export default DoctorsPage
