;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh)
(use Main)
(use BalloonTalker)
(use TWRoom)
(use Scaler)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm200 0
	offScreenTalker 1
)

(instance rm200 of ADRoom
	(properties
		picture 200
		north 240
		east 230
		vanishingY -100
	)
	
	(method (init)
		(super init: &rest)
		(Load RES_SOUND 1500)
		(if
			(and
				(not (== prevRoomNum 212))
				(not (== prevRoomNum 381))
			)
			(ego
				x: -30
				y: -30
				init:
				normalize:
				ignoreActors: TRUE
				setScale: Scaler 22 150 189 0
			)
			((ScriptID 895 1)
				x: -30
				y: -30
				init:
				setScale: Scaler 22 150 189 0
			)
		)
		(switch prevRoomNum
			(212
				(if (== (Random 1 10) 5) (flag y: 28) (flag2 y: 39))
				(flag setCycle: Forward init:)
				(flag2 setCycle: Forward init:)
				(coach loop: 0 cel: 0 init:)
				(self setScript: overTheHills)
			)
			(else 
				(self setScript: sFallFromSky)
			)
		)
	)
)

(instance overTheHills of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 215 setLoop: -1 play:)
				(= seconds 2)
			)
			(1
				(coach setCycle: EndLoop self)
			)
			(2
				(coach
					x: 36
					y: 143
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(messager say: 1 0 1 1 5 self)
			)
			(4
				(theMusic2 number: 902 setLoop: 1 play: self)
				(messager say: 1 0 1 6 self)
			)
			(5)
			(6
				(theMusic2 number: 2105 setLoop: 1 play: self)
				(messager say: 1 0 1 7 self)
			)
			(7)
			(8
				(coach
					x: 84
					y: 126
					setLoop: 2
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(9
				(theMusic fade:)
				(curRoom newRoom: 212)
			)
		)
	)
)

(instance sFallFromSky of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
				(theMusic number: 200 setLoop: 1 play:)
				(theMusic2 number: 1500 setLoop: 1 play: self)
			)
			(1
				(ego
					view: 155
					setLoop: 3
					setCycle: Forward
					setMotion: JumpTo 140 170 self
				)
				(= ticks 5)
			)
			(2
				((ScriptID 895 1)
					view: 155
					setCycle: Forward
					setLoop: 4
					setMotion: JumpTo 140 170 curRoom
				)
			)
			(3)
			(4 (curRoom newRoom: 214))
		)
	)
)

(instance pigeon of Actor
	(properties
		view 200
		loop 4
	)
)

(instance coach of Prop
	(properties
		x 71
		y 154
		view 200
	)
)

(instance flag of Prop
	(properties
		x 82
		y 20
		view 200
		loop 3
	)
)

(instance flag2 of Prop
	(properties
		x 283
		y 29
		view 200
		loop 4
	)
)

(instance offScreenTalker of BalloonTalker
	(properties
		x 49
		y 120
		talkWidth 150
	)
)
