;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh)
(use Main)
(use Procs)
(use PQRoom)
(use RangeOsc)
(use WalkTalk)
(use Magnifier)
(use Motion)
(use Actor)
(use System)

(public
	titleScreen 0
)

(instance titleScreen of PQRoom
	(properties
		picture 10
	)
	
	(method (init)
		(Load RES_VIEW 100)
		(Load RES_VIEW 103)
		(Load RES_PIC 11)
		(Load RES_WAVE 2040)
		(theMusic number: 100 loop: -1 play:)
		(super init: &rest)
		(self setScript: demoScript)
	)
)

(instance demoScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(swatPup1 init: setCycle: EndLoop self)
			)
			(2
				(swatPup2 init: setCycle: EndLoop self)
			)
			(3
				(soundFx number: 2040 loop: 1 play:)
				(flash init: cycleSpeed: 6 setCycle: EndLoop self)
				(theMusic number: 200 loop: 1 play:)
			)
			(4
				(swatPup2 dispose:)
				(swatPup1 view: 111 loop: 0 cel: 0 posn: 9 175)
				(flash dispose:)
				(curRoom drawPic: 11 SHOW_PLAIN)
				(= cycles 2)
			)
			(5
				(swatPup1 setCycle: EndLoop self)
			)
			(6
				(UpdateScreenItem swatPup1)
				(sniperScope
					setLoop: 0 1
					cel: 1
					init:
					setPri: 200
					moveSpeed: 4
					setMotion: MoveTo 195 83 self
				)
			)
			(7
				(sniperScope setCycle: RangeOsc -1 1)
				(WalkieTalkie showFrame: 11 0 179 15 -90)
				(= ticks 50)
			)
			(8
				(StopRobot 0)
				(tempActor posn: 1 15 setPri: 1 init: hide:)
				(PlayRobot 11 179 15 tempActor 10)
			)
			(9
				(soundFx init:)
				(sniperScope setCycle: 0)
			)
			(10
				(StopRobot 0)
				(holdFrame posn: 179 20 setPri: 1 setLoop: 1 cel: 0 init:)
				(Load RES_PIC 8)
				(Load RES_PIC 12)
				(Load RES_SCRIPT 12)
				(theMusic fade: 53 18 10 0 self)
			)
			(11
				(soundFx number: 1000 loop: 1 play: 127 self)
				(= ticks 140)
			)
			(12
				(holdFrame dispose:)
				(sniperScope dispose:)
				(swatPup1 dispose:)
				(StopRobot)
				(curRoom drawPic: 8 SHOW_PLAIN)
			)
			(13
				(soundFx stop:)
				(curRoom newRoom: 12)
			)
		)
	)
)

(instance swatPup1 of Prop
	(properties
		x 173
		y 180
		view 100
	)
)

(instance swatPup2 of Prop
	(properties
		x 296
		y 192
		view 102
	)
)

(instance flash of Prop
	(properties
		x 192
		y 173
		priority 200
		fixPriority TRUE
		view 103
	)
)

(instance sniperScope of Actor
	(properties
		x 320
		y 83
		view 112
	)
)

(instance snipeMag of Magnifier
	(properties
		x 179
		y 20
	)
)

(instance holdFrame of View
	(properties
		view 113
		loop 1
	)
)

(instance tempActor of Actor
	(properties
		view 112
	)
)
