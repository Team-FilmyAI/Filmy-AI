import "../../styles/LandingPage/Hero.css";
import { useEffect, useRef } from "react";
import Typed from "typed.js";

export default function Hero() {
  const typedRef = useRef(null);

  useEffect(() => {
    const typedStrings = ["Actors and Artists", "Producers and Directors", "Screenwriters and Technicians"];

    const typed = new Typed(typedRef.current, {
      strings: typedStrings,
      loop: true,
      typeSpeed: 100,
      backSpeed: 50,
      backDelay: 2000,
    });

    return () => {
      typed.destroy();
    };
  }, []);

  return (
    <div id="hero" className="hero">
      <div className="hero-title container">
        <h2>We work with!</h2>
        <p>
          <span className="typed" ref={typedRef}></span>
        </p>
      </div>
      <div className="work-group">
        {Array.from({ length: 25 }).map((_, index) => (
          <div key={index} className="diamond"></div>
        ))}
      </div>
    </div>
  );
}
