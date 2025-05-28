import { useEffect, useRef } from "react";
import { Link } from "react-router-dom";
import "../../styles/LandingPage/Navbar.css";

export default function LandingPageNavBar() {
  const sidebarRef = useRef(null);
  const hamBtnRef = useRef(null);
  const navbarContainerRef = useRef(null);
  const navbarRef = useRef(null);
  const navContainerRef = useRef(null);

  // Resize handler for navbar container responsiveness
  useEffect(() => {
    const handleResize = () => {
      const navbarContainer = navbarContainerRef.current;
      if (!navbarContainer) return;

      if (window.innerWidth >= 768) {
        navbarContainer.classList.add("container");
      } else {
        navbarContainer.classList.remove("container");
      }
    };

    handleResize(); // run on mount
    window.addEventListener("resize", handleResize);

    return () => window.removeEventListener("resize", handleResize);
  }, []);

  // Sidebar toggle + sidebar nav active switching
  useEffect(() => {
    const sidebar = sidebarRef.current;
    const hamBtn = hamBtnRef.current;

    const toggleMenu = () => {
      sidebar.classList.toggle("menu-visible");
    };

    hamBtn?.addEventListener("click", toggleMenu);

    const handleSidebarNavClick = (event) => {
      if (event.target.classList.contains("nav-item")) {
        const currentActive = sidebar.querySelector(".active");
        if (currentActive) {
          currentActive.classList.remove("active");
        }
        event.target.classList.add("active");
      }
    };

    sidebar?.addEventListener("click", handleSidebarNavClick);

    return () => {
      hamBtn?.removeEventListener("click", toggleMenu);
      sidebar?.removeEventListener("click", handleSidebarNavClick);
    };
  }, []);

  // Top navbar scroll effect + nav link active handling
  useEffect(() => {
    const navbar = navbarRef.current;
    const navContainer = navContainerRef.current;

    const handleScroll = () => {
      if (window.scrollY > 0) {
        navbar.classList.add("scrolled");
      } else {
        navbar.classList.remove("scrolled");
      }
    };

    const handleNavClick = (event) => {
      if (event.target.classList.contains("nav-item")) {
        const currentActive = navContainer.querySelector(".active");
        if (currentActive) {
          currentActive.classList.remove("active");
        }
        event.target.classList.add("active");
      }
    };

    handleScroll(); // Run once on mount

    window.addEventListener("scroll", handleScroll);
    navContainer?.addEventListener("click", handleNavClick);

    return () => {
      window.removeEventListener("scroll", handleScroll);
      navContainer?.removeEventListener("click", handleNavClick);
    };
  }, []);

  return (
    <>
      <nav>
        <div id="navbar" ref={navbarRef} className="custom-navbar">
          <div ref={navbarContainerRef} className="navbar-container container">
            <div className="logo-container">
              <img src="/images/footer/FilmyAI_logo.png" alt="logo" />
            </div>
            <div id="ham-btn" ref={hamBtnRef} className="ham-btn">
              <i className="fa fa-bars"></i>
            </div>
            <div id="nav-container" ref={navContainerRef} className="menu-container">
              <ul>
                <li>
                  <a href="#hero" className="nav-item active">
                    Home
                  </a>
                </li>
                <li>
                  <a href="#about" className="nav-item">
                    About
                  </a>
                </li>
                <li>
                  <a href="#demo" className="nav-item">
                    Demo
                  </a>
                </li>
                <li>
                  <a href="#blog" className="nav-item">
                    Blogs
                  </a>
                </li>
              </ul>
            </div>
            <div className="user-container">
              <ul>
                <li>
                  <Link to="/Login">Login</Link>
                </li>
                <li>
                  <Link to="/SignUp">SignUp</Link>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </nav>

      <div id="sidebar" ref={sidebarRef} className="sidebar">
        <ul className="sidebar-nav">
          <li>
            <a href="#hero" className="nav-item active">
              Home
            </a>
          </li>
          <li>
            <a href="#about" className="nav-item">
              About
            </a>
          </li>
          <li>
            <a href="#demo" className="nav-item">
              Demo
            </a>
          </li>
          <li>
            <a href="#blog" className="nav-item">
              Blogs
            </a>
          </li>
          <li>
            <Link to="/Login" className="nav-item">
              Login
            </Link>
          </li>
        </ul>
      </div>
    </>
  );
}
