const NavBar = () => {
  return (
    <header>
      <nav>
        <ul className="flex justify-between py-6 px-6">
          <li>logo</li>
          <div className="flex gap-8 text-lg">
            <li className="cursor-pointer">Login</li>
            <li className="cursor-pointer">About</li>
            <li className="cursor-pointer">Plans</li>
          </div>
        </ul>
      </nav>
    </header>
  );
};
export default NavBar;
