;;; Sierra Script 1.0 - (do not remove this comment)
(script# 110)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm110 0
)

(local
	scriptTimer
)
(instance rm110 of Room
	(properties
		picture 130
	)
	
	(method (init)
		(fakeEgo init: setScript: egoScript)
		(super init:)
		(theMusic
			number: 902
			loop: -1
			vol: 127
			priority: 50
			playBed:
		)
		(jukebox init: setCycle: Forward)
		(lefty init:)
		(fan init: setCycle: Forward)
		(self setScript: sRoomScript)
	)
)

(instance sRoomScript of Script
	(method (doit)
		(super doit: &rest)
		(if (and (== state 20) (> (GetTime SYSTIME1) scriptTimer))
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(DoDisplay3 {Your mission as Larry Laffer:}
					156 29 colBlack
				)
				(= seconds 3)
			)
			(2
				(DoDisplay3 {seek out new civilizations...}
					170 29 colBlack
				)
				(= seconds 2)
			)
			(3
				(lefty setCycle: EndLoop self)
				(soundFX number: 113 loop: 1 vol: 127 priority: 10 play:)
			)
			(4
				(lefty posn: 232 110 loop: 3 cel: 0 setCycle: EndLoop self)
				(soundFX number: 114 play:)
			)
			(5
				(soundFX number: 115 play:)
				(= cycles 10)
			)
			(6
				(soundFX number: 113 play:)
				(lefty posn: 208 110 loop: 4 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(7
				(soundFX number: 114 play:)
				(lefty setCycle: EndLoop self)
			)
			(8
				(soundFX number: 115 play:)
				(cast eachElementDo: #z 1000)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 140)
				(leftEye init:)
				(rightEye init:)
				(nose init: setScript: sNoseScript)
				(= cycles 2)
			)
			(9
				(leftEye setCycle: EndLoop)
				(rightEye setCycle: EndLoop self)
			)
			(10
				(DoDisplay3 {...explore new life forms...}
					136 29 colBlack
				)
				(= seconds 3)
			)
			(11
				(mouth init:)
				(= cycles 10)
			)
			(12
				(mouth cycleSpeed: 1 setCycle: EndLoop)
				(= cycles 20)
			)
			(13
				(rightEye setCycle: EndLoop)
				(soundFX number: 382 play:)
				(= cycles 20)
			)
			(14
				(nose setScript: 0)
				(cast eachElementDo: #z 1000)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 150)
				(theMusic2
					number: 130
					loop: -1
					vol: 127
					priority: 5
					play:
				)
				(fakeEgo
					init:
					z: 0
					x: 171
					y: 111
					view: 150
					cel: 0
					setLoop: 0
				)
				(= seconds 3)
			)
			(15
				(fakeEgo setCycle: EndLoop self)
				(soundFX number: 132 play:)
			)
			(16
				(= cycles 10)
			)
			(17
				(fakeEgo setCycle: EndLoop self)
				(soundFX number: 132 play:)
			)
			(18
				(= cycles 10)
			)
			(19
				(= scriptTimer (+ (GetTime SYSTIME1) 4))
				(= cycles 1)
			)
			(20
				(DoDisplay3 {...and boldly go where no man has gone before!}
					166 28 colRed 29 colDRed
				)
				(soundFX number: 133 play:)
				(UnLoad VIEW 140)
				(UnLoad VIEW 130)
				(UnLoad PICTURE 130)
				(UnLoad PICTURE 140)
				(UnLoad PICTURE 120)
				(UnLoad SOUND 900)
				(UnLoad SOUND 113)
				(UnLoad SOUND 114)
				(UnLoad SOUND 115)
				(UnLoad SOUND 382)
				(Load PICTURE 160)
				(Load VIEW 160)
				(Load SOUND 901)
				(fakeEgo setCycle: EndLoop)
				(soundFX number: 132 play:)
			)
			(21
				(theMusic fade:)
				(theMusic2 stop:)
				(curRoom newRoom: 390)
			)
		)
	)
)

(instance jukebox of Prop
	(properties
		x 36
		y 122
		view 130
		cel 1
	)
)

(instance lefty of Prop
	(properties
		x 202
		y 110
		view 130
		loop 2
		cycleSpeed 1
	)
)

(instance fan of Prop
	(properties
		x 182
		view 130
		loop 1
		cel 5
	)
)

(instance rightEye of Prop
	(properties
		x 128
		y 61
		view 140
		loop 1
		signal ignrAct
	)
)

(instance leftEye of Prop
	(properties
		x 156
		y 58
		view 140
		signal ignrAct
	)
)

(instance mouth of Prop
	(properties
		x 143
		y 80
		view 140
		loop 2
		cel 2
		signal ignrAct
	)
)

(instance nose of Prop
	(properties
		x 140
		y 79
		view 140
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
	)
)

(instance fakeEgo of Prop
	(properties
		x 208
		y 127
		view 130
		loop 5
	)
)

(instance egoScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 20)
			)
			(1
				(fakeEgo setCycle: EndLoop self)
			)
			(2
				(= register (fakeEgo cel?))
				(fakeEgo loop: 6 cel: 0 setCycle: Forward)
				(= cycles 10)
			)
			(3
				(fakeEgo loop: 7 cel: 0 setCycle: EndLoop self)
			)
			(4
				(fakeEgo loop: 5 cel: 0)
				(self init:)
			)
		)
	)
)

(instance sNoseScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(nose setCycle: EndLoop)
				(= cycles 9)
			)
			(1
				(nose setCycle: BegLoop)
				(= cycles 15)
			)
			(2 (self init:))
		)
	)
)

(instance soundFX of Sound)
