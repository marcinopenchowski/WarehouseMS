import React, { useState } from "react";

export default function Navbar() {
  const [open, setOpen] = useState(true);
  const Menus = [
    { title: "Dashboard", src: "dashboard", path: "/" },
    { title: "Products", src: "search", path: "/products"},
    { title: "User", src: "user", path: "/login"},
    { title: "Settings", src: "settings", path: "/settings"},
  ];

  return (
    <div
      className={`${
        open ? "w-72" : "w-20"
      } duration-300 h-screen bg-zinc-700 relative p-5 pt-8`}
    >
      <img
        src="/assets/images/control.png"
        className={` absolute cursor-pointer -right-3 top-9 w-7 border-2 border-zinc-700 rounded-full ${
          !open && "rotate-180"
        }`}
        alt="hide"
        onClick={() => setOpen(!open)}
      />
      <div className="flex gap-x-4 items-center">
        <img
          src="/assets/images/logo.png"
          className={`cursor-pointer duration-500 ${open && "rotate-[360deg]"}`}
          alt="logo"
        />
        <h1
          className={` text-white origin-left font-medium text-2xl duration-300 text-center font-mono${
            !open && "scale-0"
          }`}
        >
          WarehouseMS
        </h1>
      </div>
      <ul className="pt-6">
        {Menus.map((menu, index) => (
          <a href={menu.path}><li
            key={index}
            className={` text-orange-500 text-sm flex items-center gap-x-4 cursor-pointer p-2 hover:bg-slate-500 rounded-md font-bold`}>
            <img src={`/assets/images/${menu.src}.png`} alt="img" />
            <span className={`${!open && "hidden"} origin-left duration-200 `}>
              {menu.title}
            </span>
          </li>
          </a>
        ))}
      </ul>
    </div>
  );
}

