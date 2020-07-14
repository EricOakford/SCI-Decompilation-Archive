;;; Sierra Script 1.0 - (do not remove this comment)
(script# 122)
(include game.sh)
(use Main)
(use RandCyc)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demo3 0
)

(local
	mD
)
(procedure (JimSays theString &tmp [temp0 3])
	(if mD (Display 122 0 p_restore mD) (= mD 0))
	(if theString
		(= mD
			(Display theString
				p_width 130
				p_at 10 5
				p_mode teJustLeft
				p_font 4
				p_color 7
				p_save
			)
		)
	)
)

(instance demo3 of Room
	(properties
		picture 5
		style FADEOUT
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(JHead init:)
		(JLHand setScript: (armScript new:) init:)
		(JRHand setScript: (armScript new:) init:)
		(JEye setScript: (eyeScript new:) init:)
		(switch prevRoomNum
			(121 (self setScript: jimTalk))
			(else 
				(self setScript: demoScript)
			)
		)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
	)
	
	(method (dispose)
		(DisposeScript RANDCYC)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(else (event claimed: TRUE))
		)
	)
)

(instance jimTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(music number: 51 loop: 1 play:)
				(= cycles 2)
			)
			(1
				(JimSays
					{Hello, I'm Jim Walls, retired highway patrol officer and creator of the Police Quest series.}
				)
				(JHead setCycle: RandCycle)
				(= seconds 7)
			)
			(2
				(JimSays
					{The news of the stabbing you just witnessed is being radioed, even as we speak, to one Sonny Bonds, Lytton City police officer.}
				)
				(JLHand cycleSpeed: 12)
				(= seconds 8)
			)
			(3
				(JimSays
					{And Sonny, he's liable to take it kind of personal.}
				)
				(= seconds 7)
			)
			(4
				(JimSays
					{Not only is it the first attempted murder to occur since his promotion to Detective Sergeant...}
				)
				(= seconds 7)
			)
			(5
				(JimSays
					{...but the attractive lady hospitalized with multiple chest wounds is his wife, Marie.}
				)
				(= seconds 7)
			)
			(6
				(JimSays 0)
				(JHead setCycle: 0 dispose:)
				(JLHand setScript: 0)
				(JRHand setScript: 0)
				(JLHand dispose:)
				(JEye dispose:)
				(music fade: self)
			)
			(7
				(curRoom drawPic: 98)
				(cast eachElementDo: #dispose)
				(= cycles 5)
			)
			(8 (curRoom newRoom: 123))
		)
	)
)

(instance demoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(music number: 51 loop: 1 play:)
				(= cycles 2)
			)
			(1
				(JimSays
					{Is the difference between living and dying.}
				)
				(JHead setCycle: RandCycle)
				(= seconds 5)
			)
			(2
				(JimSays
					{Police Quest III. Incredible VGA digitized graphics. All original soundtrack. Icon-driven, no-typing interface.}
				)
				(JHead setCycle: RandCycle)
				(= seconds 7)
			)
			(3
				(JimSays
					{And heart-pounding action that never compromises on true police procedure.}
				)
				(= seconds 7)
			)
			(4
				(JimSays
					{Police Quest III. Because being Sonny Bonds is as close to real life as you'll ever want to get.}
				)
				(= seconds 7)
			)
			(5
				(JimSays 0)
				(JHead setCycle: 0 dispose:)
				(JLHand setScript: 0)
				(JRHand setScript: 0)
				(JLHand dispose:)
				(JEye dispose:)
				(= seconds 2)
			)
			(6
				(curRoom drawPic: 98)
				(cast eachElementDo: #dispose)
				(= cycles 5)
			)
			(7 (curRoom newRoom: 121))
		)
	)
)

(instance eyeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: BegLoop self))
			(1
				(= seconds (Random 3 10))
				(= state -1)
			)
		)
	)
)

(instance armScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCel: (if (== (client cel?) 0) 1 else 0))
				(= cycles 5)
			)
			(1
				(= seconds (Random 3 10))
				(= state -1)
			)
		)
	)
)

(instance JHead of Prop
	(properties
		x 164
		y 40
		view 100
		priority 12
		signal fixPriOn
		cycleSpeed 9
	)
)

(instance JLHand of Prop
	(properties
		x 195
		y 127
		view 100
		loop 3
		priority 12
		signal fixPriOn
		cycleSpeed 9
	)
)

(instance JRHand of Prop
	(properties
		x 111
		y 100
		view 100
		loop 4
		priority 12
		signal fixPriOn
		cycleSpeed 9
	)
)

(instance JEye of Prop
	(properties
		x 166
		y 25
		view 100
		loop 1
		priority 12
		signal fixPriOn
		cycleSpeed 9
	)
)
