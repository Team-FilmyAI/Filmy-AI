import { Link } from "react-router-dom";
import "../../styles/LoginSignUp/Login.css";

export default function Login() {
  return (
    <div className="login-body">
      <div className="login-container">
        <div className="login-left">
          <h2 className="login-title">Login</h2>
          <form className="login-form">
            <input type="text" placeholder="Email address or Username" className="input-field" />
            <input type="password" placeholder="Password" className="input-field" />
            <div className="forgot-password">
              <Link to="/Forgot">Forgot Password?</Link>
            </div>
            <button className="login-button">Log in</button>
            <p className="signup-link">
              Don't have an account? <Link to="/Signup">Sign Up</Link>
            </p>
            <div className="divider">
              <span>OR</span>
            </div>

            <div className="social-icons">
              <i className="fab fa-google"></i>
              <i className="fab fa-facebook-f"></i>
              <i className="fab fa-instagram"></i>
            </div>
          </form>
        </div>
        <div className="login-right">
          <h1 className="login-org-name">FilmyAI</h1>
          <p className="login-org-tag-line">Start your journey today!</p>
        </div>
      </div>
    </div>
  );
}
