import "../../styles/LandingPage/LandingPage.css";
import LandingPageNavBar from "./NavBar";
import Hero from "./Hero";
import Chat from "./Chat";
import About from "./About";
import Demo from "./Demo";
import Blog from "./Blog";
import Footer from "./Footer";

export default function LandingPage() {
  return (
    <>
      <LandingPageNavBar />
      <main>
        <div className="main">
          <div className="main-container">
            <Hero />
            <About />
            <Demo />
            <Blog />
          </div>
          <Chat />
        </div>
        <Footer />
      </main>
    </>
  );
}
