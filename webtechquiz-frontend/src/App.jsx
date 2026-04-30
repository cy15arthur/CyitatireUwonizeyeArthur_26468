import { useState } from 'react'
import DoctorsPage from './pages/DoctorsPage'
import AppointmentsPage from './pages/AppointmentsPage'

function App() {
  const [activePage, setActivePage] = useState('doctors')

  return (
    <div className="min-h-screen bg-gray-100">

      <nav className="bg-blue-600 text-white px-6 py-4 flex items-center justify-between shadow-md">
        <span className="text-xl font-bold">🏥 Hospital Appointments</span>
        <div className="flex gap-3">
          <button
            onClick={() => setActivePage('doctors')}
            className={`px-4 py-2 rounded border-2 border-white font-medium transition-colors ${
              activePage === 'doctors'
                ? 'bg-white text-blue-600'
                : 'bg-transparent text-white hover:bg-blue-500'
            }`}
          >
            Doctors
          </button>
          <button
            onClick={() => setActivePage('appointments')}
            className={`px-4 py-2 rounded border-2 border-white font-medium transition-colors ${
              activePage === 'appointments'
                ? 'bg-white text-blue-600'
                : 'bg-transparent text-white hover:bg-blue-500'
            }`}
          >
            Appointments
          </button>
        </div>
      </nav>

      <main className="max-w-5xl mx-auto px-4 py-8">
        {activePage === 'doctors' && <DoctorsPage />}
        {activePage === 'appointments' && <AppointmentsPage />}
      </main>

    </div>
  )
}

export default App
