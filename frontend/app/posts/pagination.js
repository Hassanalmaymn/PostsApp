export default function Pagination({ page, totalPages, onNext, onPrevious }) {
  return (
    <div className="flex justify-center mt-10">
      <button
        className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        onClick={onPrevious}
      >
        Previous
      </button>
      <div className="flex justify-center items-center">
        <span className="mx-2">
          Page {++page} of {totalPages}
        </span>
      </div>
      <button
        className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        onClick={onNext}
      >
        Next
      </button>
    </div>
  );
}
