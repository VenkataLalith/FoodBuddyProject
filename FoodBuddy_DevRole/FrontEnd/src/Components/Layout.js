import React from 'react';
import './Layout.css';
import Navbar from './Navbar';


const Layout = ({ children }) => {
  return (
    <div>
      {/* <nav style={{ display: 'flex', justifyContent: 'flex-start', alignItems: 'center', backgroundColor: '#e6e6e6', margin: 0, padding: '1rem' }}> */}
        {/* Navigation bar content */}
        {/* <ul style={{ listStyle: 'none', display: 'flex', gap: '1rem', margin: 0, padding: 0 }}>
          <li><a href="/">Home</a></li>
          <li><a href="/">Login</a></li>
          <li><a href="/register">Register</a></li>
        </ul>
      </nav> */}
      <Navbar/>
      <main style={{ height: '100%', marginBottom: '2rem', display: 'flex' }}>
        {children}
      </main>
      <footer style={{ backgroundColor: '#e6e6e6', margin: 0, padding: '1rem' }}>
        {/* Footer content */}
        <p>FoodBuddy App</p>
      </footer>
    </div>
  );
};


export default Layout;
