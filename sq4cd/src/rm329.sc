;;; Sierra Script 1.0 - (do not remove this comment)
(script# 329)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use MoveCyc)
(use LoadMany)
(use Motion)
(use User)
(use System)

(public
	rm329 0
)

(local
	[subCycle 21] = [0 0 164 122 0 1 166 120 0 2 168 118 0 0 170 116 0 1 172 114 -32768]
	[subTopCycle 21] = [1 0 161 125 1 1 163 123 1 2 165 121 1 3 167 119 1 4 169 117 -32768]
)
(instance rm329 of SQRoom
	(properties)
	
	(method (init)
		(Load PICTURE 803)
		(UnLoad PICTURE 803)
		(rm329 picture: 329)
		(Load VIEW 330)
		(LoadMany SOUND 127 128)
		(theArea init:)
		(music fade: 0 2 1 1)
		(globalSound
			number: 127
			vol: 1
			loop: -1
			play:
			fade: 127 1 1 0
		)
		(super init:)
		(ShowStatus 10)
		(self setScript: rmScript)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(theIconBar disable: ICON_WALK ICON_TALK ICON_DO ICON_SMELL ICON_TASTE)
				(sub init: setCycle: Forward setMotion: MoveTo 164 122 self)
			)
			(1
				(sub setCycle: MoveCycle @subCycle)
				(globalSound number: 128 vol: 127 play: hold: 1)
				(subTop init: setCycle: MoveCycle @subTopCycle self)
			)
			(2
				(sub setCycle: Forward setMotion: MoveTo 345 114)
				(subTop setMotion: MoveTo 345 117 self)
			)
			(3 (= cycles 2))
			(4
				(HandsOff)
				(if modelessDialog (modelessDialog dispose:))
				(curRoom drawPic: 803 SCROLLLEFT)
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance sub of Sq4Actor
	(properties
		x 11
		y 126
		yStep 1
		view 330
		priority 2
		signal (| ignrAct fixedLoop)
		xStep 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 321 say: 7)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance subTop of Sq4Actor
	(properties
		x -25
		y 117
		yStep 1
		view 330
		loop 1
		cel 4
		priority 10
		signal (| ignrAct fixedLoop fixPriOn)
		xStep 2
	)
	
	(method (doVerb theVerb)
		(sub doVerb: theVerb &rest)
	)
)

(instance theArea of Sq4Feature
	(properties
		x 156
		y 88
		nsBottom 200
		nsRight 320
		sightAngle 180
		lookStr 2
	)
)
