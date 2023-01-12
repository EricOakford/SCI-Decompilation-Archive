;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include game.sh)
(use Main)
(use Intrface)
(use Osc)
(use RandCyc)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm50 0
)

(local
	i
	egoPosn = [0 0 0 3 118 34 3 130 41 4 139 50 5 149 57 6 155 65 7 162 72 8 168 78 9 173 84 10 178 89 11 183 92 12 186 95 13 190 98 14 193 100 15 196 102 -1 -1 -1]
)
(instance rm50 of Room
	(properties
		picture 50
	)
	
	(method (init)
		(ego normal: 0 posn: 92 60 init: hide:)
		(hatch init: hide:)
		(super init:)
		(HandsOff)
		(theMusic number: 501 loop: -1 play:)
		(ship init:)
		(flames init:)
		(self setScript: shipArrives)
	)
)

(instance floatAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setLoop: 3
					cel: 0
					posn: 76 17
					setCycle: Forward
					moveSpeed: 5
					cycleSpeed: 9
					setStep: 3 3
					setMotion: MoveTo -25 27 self
				)
			)
			(1
				(curRoom drawPic: 16 -32759)
				(ship dispose:)
				(hatch dispose:)
				(= seconds 1)
			)
			(2
				(ego posn: 360 27 setMotion: MoveTo -40 50)
				(= seconds 5)
			)
			(3
				(EgoDead 948 0 0 50 0)
			)
		)
	)
)

(instance shipArrives of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ship setStep: 6 6 setMotion: MoveTo 228 180 self)
				(theMusic2 number: 533 loop: -1 play:)
				(flames cycleSpeed: 4 setCycle: RandCycle)
			)
			(1
				(flames setCycle: BegLoop)
				(ship setStep: 4 4 setMotion: MoveTo 176 150 self)
			)
			(2
				(theMusic2 pause:)
				(flames dispose:)
				(ship setStep: 2 2 setMotion: MoveTo 117 112 self)
			)
			(3
				(ship setCel: 1)
				(hatch show: setCycle: EndLoop self)
			)
			(4
				(if (ego has: iJetpack)
					(Print 50 1)
				else
					(Print 50 2)
				)
				(= cycles 24)
			)
			(5
				(ego
					show:
					view: 33
					setLoop: (if (ego has: iJetpack) 0 else 2)
					cel: 0
					cycleSpeed: 8
					setCycle: CycleTo 3 1 self
				)
			)
			(6
				(if (ego has: iJetpack)
					(= cycles 30)
				else
					(client setScript: floatAway)
				)
			)
			(7
				(soundFx number: 502 loop: 1 play: hold: 1 fade:)
				(ego
					posn: 105 25
					loop: 1
					cel: 0
					cycleSpeed: 5
					setCycle: CycleTo 2 1 self
				)
			)
			(8
				(if (!= [egoPosn (= i (+ i 3))] -1)					
					(ego
						cel: [egoPosn i]
						x: [egoPosn (+ i 1)]
						y: [egoPosn (+ i 2)]
					)
					(-- state)
				)
				(= cycles 2)
			)
			(9
				(ego
					setLoop: (ego loop?)
					setCel: (ego cel?)
					setMotion: MoveTo 205 103 self
				)
			)
			(10
				(ego hide:)
				(soundFx stop:)
				(hatch setCycle: BegLoop self)
			)
			(11
				(hatch dispose:)
				(theMusic2 pause: 0)
				(flames init: cycleSpeed: 2 setCycle: Oscillate)
				(ship setCel: 0 setStep: 2 2 setMotion: MoveTo 70 88 self)
			)
			(12
				(ship setStep: 4 4 setMotion: MoveTo 30 64 self)
			)
			(13
				(ship setStep: 7 7 setMotion: MoveTo -36 22 self)
			)
			(14
				(ship dispose:)
				(theMusic2 stop:)
				(flames dispose:)
				(= cycles 3)
			)
			(15
				(ego setLoop: -1 setCel: -1)
				(curRoom newRoom: 51)
			)
		)
	)
)

(instance star0 of Prop
	(properties
		x 59
		y 144
		view 150
		loop 3
		cel 2
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 2
	)
)

(instance star1 of Prop
	(properties
		x 267
		y 26
		view 150
		loop 3
		cel 1
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 4
	)
)

(instance star2 of Prop
	(properties
		x 293
		y 17
		view 150
		loop 3
		cel 1
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 6
	)
)

(instance hatch of Prop
	(properties
		x 94
		y 94
		view 150
		loop 1
		priority 10
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 8
	)
)

(instance flames of Prop
	(properties
		x 307
		y 223
		view 150
		loop 2
		priority 3
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		cycleSpeed 2
	)
	
	(method (doit)
		(= x (ship x?))
		(= y (ship y?))
		(super doit: &rest)
	)
)

(instance ship of Actor
	(properties
		x 307
		y 223
		view 150
		priority 3
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		moveSpeed 1
	)
)
