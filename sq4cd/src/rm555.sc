;;; Sierra Script 1.0 - (do not remove this comment)
(script# 555)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use ForCount)
(use Sound)
(use Motion)
(use System)

(public
	rm555 0
)

(instance rm555 of SQRoom
	(properties
		picture 555
	)
	
	(method (init)
		(super init:)
		(Load VIEW 557)
		(Load SOUND 813)
		(holo init: hide:)
		(self setScript: realyStupidScript)
	)
)

(instance realyStupidScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 3))
			(1
				(UnLoad PICTURE 556)
				(= cycles 3)
			)
			(2
				(aSound number: 813 loop: 1 init: play:)
				(holo show: cycleSpeed: 4 setCycle: CycleTo 1 1 self)
			)
			(3
				(holo hide:)
				(hair init: setCycle: ForwardCounter 40)
				(= seconds 2)
			)
			(4
				(aSound stop:)
				(= seconds 1)
			)
			(5
				(tROGJR
					say: 13 self 2
						#at 5 20
						#color myTextColor20
						#back myLowlightColor
						#mode teJustCenter
						#width 125
				)
			)
			(6
				(tROGER
					say: 5 self 2
					#at 5 20
					#color myTextColor10
					#back myLowlightColor
					#mode teJustCenter
					#width 125
				)
			)
			(7
				(tROGJR
					say: 14 self 2
						#at 5 20
						#color myTextColor20
						#back myLowlightColor
						#mode teJustCenter
						#width 125
				)
			)
			(8
				(aSound number: 813 loop: 1 init: play:)
				(hair hide:)
				(holo show:)
				(= cycles 4)
			)
			(9
				(holo setCycle: CycleTo 0 -1 self)
			)
			(10
				(holo dispose:)
				(UnLoad VIEW 557)
				(= seconds 2)
			)
			(11
				(aSound stop:)
				(curRoom newRoom: 556)
			)
		)
	)
)

(instance hair of Sq4Prop
	(properties
		x 176
		y 32
		sightAngle 180
		view 557
		loop 1
		priority 7
		signal (| ignrAct fixPriOn)
		lookStr 2
	)
)

(instance holo of Sq4Prop
	(properties
		x 167
		y 120
		sightAngle 180
		view 557
		priority 6
		signal (| ignrAct fixPriOn)
		lookStr 4
	)
)

(instance aSound of Sound
	(properties
		number 813
	)
)

(instance tROGER of Sq4Narrator
	(properties
		noun ROGER
		modNum 557
		modeless TRUE
		talkerNum ROGER
	)
)

(instance tROGJR of Sq4Narrator
	(properties
		noun ROGERJR
		modNum 557
		disposeWhenDone FALSE
		modeless TRUE
		talkerNum ROGERJR
	)
)
