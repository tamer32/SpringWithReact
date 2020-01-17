import React from "react";
import { Formik, Field } from "formik";
import * as Yup from "yup";
const validationSchema = Yup.object().shape({
  username: Yup.string()
    .min(1, "Must have a character")
    .max(40, "Must be shorter than 40")
    .required("Must enter name")
});
function Login() {
  return (
    <Formik
      initialValues={{ username: "" }}
      validationSchema={validationSchema}
      onSubmit={() =>
        fetch(`/login?username=${username.value}`, {
          method: "POST"
        })
          .then(response => response.json())
          .then(data =>
            data.status == 500
              ? alert("No such name exists")
              : (location.href = `${location.href}user/${data.id}`)
          )
          .catch(err => console.warn(err))
      }
    >
      {({ values, errors, touched, handleChange, handleSubmit }) => (
        <form onSubmit={handleSubmit}>
          <div className="input-row">
            <Field
              name="username"
              id="username"
              onChange={handleChange}
              value={values.username}
            />
            {errors.username && touched.username ? (
              <div>{errors.username}</div>
            ) : null}
          </div>
          <div>
            <button type="submit">Submit</button>
          </div>
        </form>
      )}
    </Formik>
  );
}
export default Login;
