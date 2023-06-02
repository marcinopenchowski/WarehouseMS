import React from "react";
import Navbar from "./shared/Navbar";
import Header from "./shared/Header";

export default function Main() {
    return (
        <div className="flex flex-row bg-neutral-100 h-screen w-screen overflow-hidden">
            <Navbar />
            <div className="flex-1">
                <Header />
            </div>
        </div>
    );
}