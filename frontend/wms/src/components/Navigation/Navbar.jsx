import React, { useState } from "react";

export default function Navbar() {
  const [open, setOpen] = useState(true);
  const Menus = [
    { title: "Dashboard", src: "dashboard", path: "/" },
    { title: "Products", src: "search", path: "/products" },
    { title: "User", src: "user", path: "/login", spacing: true },
    { title: "Settings", src: "settings", path: "/settings" },
  ];

  return (
    <div
      className={`${
        open ? "w-72" : "w-24"
      } duration-200 h-screen bg-zinc-700 relative p-4 pt-8`}
    >
      <img
        src="/assets/images/control.png"
        className={` absolute cursor-pointer -right-3 top-9 w-7 border-2 border-zinc-700 rounded-full ${
          !open && "rotate-180"
        }`}
        alt="hide"
        onClick={() => setOpen(!open)}
      />
      <a href="/">
        <div className="flex gap-x-4 items-center">
          <img
            src="/assets/images/logo.png"
            className={`cursor-pointer duration-500 ${
              open && "rotate-[360deg]"
            }`}
            alt="logo"
          />

          <h1
            className={`${
              !open && "scale-0"
            } text-white origin-left font-medium text-2xl duration-300 text-center font-mono`}
          >
            WarehouseMS
          </h1>
        </div>
      </a>
      <ul className="pt-6">
        {Menus.map((menu, index) => (
          <a href={menu.path}>
            <li
              key={index}
              className={` text-yellow-500 text-sm flex items-center gap-x-4 cursor-pointer p-2 hover:bg-slate-500 rounded-md font-bold ${
                menu.spacing ? "mt-9" : "mt-2"
              }`}
            >
              <img src={`/assets/images/${menu.src}.png`} alt="img" />
              <span
                className={`${!open && "hidden"} origin-left duration-200 `}
              >
                {menu.title}
              </span>
            </li>
          </a>
        ))}
      </ul>
    </div>
  );
}
