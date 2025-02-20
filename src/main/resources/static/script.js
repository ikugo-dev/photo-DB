import { localHost } from "./fileService.js";
import { uploadFile, downloadFile, deleteFile } from "./fileService.js";

async function fetchFiles() {
    const response = await fetch(`${localHost}/photos`, {
        method: "GET",
    });
    const images = await response.json();
    reRenderFiles(images);
}
function reRenderFiles(images) {
    const container = document.getElementById("file-container");
    container.innerHTML = ""; // Clear previous content

    images.forEach(image => {
        const file = renderFile(image);
        container.appendChild(file);
    });
}
function renderFile(image) {
    const file = document.createElement("div");
    file.className = "file";

    const img = document.createElement("img");
    // img.src = image.url; // Ensure your API provides this
    img.alt = image.fileName;
    file.appendChild(img);

    function createButton(className, icon, onClick) {
        const button= document.createElement("button");
        button.className = className;
        const buttonIcon = document.createElement("i");
        buttonIcon.className = `fa-solid ${icon}`;
        button.appendChild(buttonIcon);
        button.onclick = onClick;
        return button;
    }
    const downloadButton = createButton(
        "download-button",
        "fa-solid fa-download",
        () => downloadFile(image.id)
    )
    const deleteButton = createButton(
        "delete-button",
        "fa-solid fa-trash",
        async () => {
            let ok = await deleteFile(image.id)
            if (ok) {
                alert("Image deleted successfully!");
                await fetchFiles(); // Refresh the gallery
            } else {
                alert("Failed to delete image.");
            }
        }
    )
    const buttonGroup = document.createElement("div");
    buttonGroup.appendChild(downloadButton);
    buttonGroup.appendChild(deleteButton);
    file.appendChild(buttonGroup);
    return file;
}

document.getElementById("upload-button").addEventListener("click", async () => {
    let ok = await uploadFile();
    if (ok) {
        alert("Image uploaded successfully!");
        await fetchFiles(); // Refresh the gallery
    } else {
        alert("Failed to upload image.");
    }
});
fetchFiles().then(() => alert("yay"));