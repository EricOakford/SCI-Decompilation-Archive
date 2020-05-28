;;; Sierra Script 1.0 - (do not remove this comment)
(script# 59)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use MoveCyc)
(use Sound)
(use Motion)
(use System)

(public
	rm059 0
)

(local
	[shipCycle 65] =
		[0 0 125 94 0 0 125 93 0 0 125 94 0 0 124 94 0 0 125 94 0 0 125 93 0 0 126 94 0 0 125 94 0 0
		125 95 0 0 125 94 0 0 126 94 0 0 125 94 0 0 125 93 0 0 125 94 0 0 124 94 0 0 125 94 -32768]
)
(instance rm059 of SQRoom
	(properties
		picture 59
		style FADEOUT
	)
	
	(method (init &tmp [temp0 50])
		(Load VIEW 22)
		(Load SOUND 110)
		(Load SCRIPT MOVECYC)
		(super init:)
		(HandsOff)
		(self setScript: rmScript)
		(shipBlast init:)
		(if (== prevRoomNum 72)
			(ship posn: 126 256)
		else
			(ship posn: 29 -5)
		)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ShowStatus 12)
				(if (!= prevRoomNum 72)
					(music number: 801 loop: 1 setVol: 127 flags: 1 playBed:)
				)
				(shipBlast number: 109 loop: 1 setVol: 127 play:)
				(ship init:)
				(= cycles 1)
			)
			(2
				(ship setMotion: MoveTo 126 94 self)
			)
			(3
				(ship setCycle: MoveCycle @shipCycle self)
			)
			(4
				(if (== prevRoomNum 72)
					(ship setCycle: MoveCycle @shipCycle self)
				else
					(self cue:)
				)
			)
			(5
				(if (== prevRoomNum 72) (ego view: 0))
				(shipBlast number: 110 loop: 1 flags: 1 play: self)
				(ship
					cycleSpeed: 2
					setCycle: EndLoop
					moveSpeed: 3
					setMotion: MoveTo 150 193 self
				)
			)
			(6 0)
			(7
				(shipBlast stop:)
				(if (== prevRoomNum 72)
					(curRoom newRoom: 119)
				else
					(curRoom newRoom: 60)
				)
			)
		)
	)
)

(instance ship of Sq4Actor
	(properties
		yStep 4
		view 22
		signal ignrHrz
		cycleSpeed 5
		xStep 4
	)
)

(instance shipBlast of Sound
	(properties)
)
