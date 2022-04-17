const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {schedule: []};
	}

	componentDidMount() {
		fetch('/2apples/api/schedule')
		.then(response => response.json())
		.then(result => {
			this.setState({schedule: result});
		});
	}

	render() {
		return (
			<Schedule schedule={this.state.schedule}/>
		)
	}
}

class Schedule extends React.Component{
	render() {
		const schedule = this.props.schedule.map(userSchedule =>
			<UserSchedule key={userSchedule.user.id} userSchedule={userSchedule}/>
		);
		return (
			<table>
				<tbody>
					<tr>
                        <th>Name</th>
                        <th>Day</th>
                        <th>Start</th>
                        <th>End</th>
				    </tr>
					{schedule}
				</tbody>
			</table>
		)
	}
}

class UserSchedule extends React.Component{
	render() {
	    const daysLength = Object.keys(this.props.userSchedule.timeIntervals).length
	    const daySchedules = Object.entries(this.props.userSchedule.timeIntervals).map(([day, intervals]) =>
            <tr>
                {day == 1 ? <td rowspan = {daysLength == 0 ? 1 : daysLength}>{this.props.userSchedule.user.name}</td> : ""}
                <td>{day}</td>
                <td>12:00</td>
                <td>17:00</td>
            </tr>
	    )
		return (
		    <React.Fragment>
                {daySchedules}
            </React.Fragment>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)