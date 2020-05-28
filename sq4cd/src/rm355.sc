;;; Sierra Script 1.0 - (do not remove this comment)
(script# 355)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use MoveCyc)
(use Sound)
(use User)
(use System)

(public
	rm355 0
)

(local
	saveBits
	[shipCycle 149] = [1 0 184 187 1 0 184 181 1 0 184 175 1 0 184 169 1 0 184 163 1 0 184 157 1 0 184 151 1 0 184 145 1 0 184 139 1 0 184 133 1 0 184 127 1 0 184 121 1 0 184 115 1 0 184 109 1 0 184 103 1 0 184 97 1 0 184 91 1 0 184 85 1 0 184 79 1 0 184 73 1 0 184 67 1 0 184 61 1 0 184 55 1 0 184 49 1 0 184 43 1 0 184 37 1 0 184 31 1 0 184 25 1 0 184 19 1 0 184 13 1 0 184 7 1 0 184 1 1 0 184 -5 1 0 184 -10 1 0 184 -20 1 0 184 -30 0 0 -100 -100 -32768]
	[door0Cycle 69] = [0 0 111 20 0 0 108 20 0 0 105 20 0 0 102 20 0 0 99 20 0 0 96 20 0 0 93 20 0 0 90 20 0 0 87 20 0 0 84 20 0 0 81 20 0 0 78 20 0 0 75 20 0 0 72 20 0 0 69 20 0 0 66 20 0 0 62 20 -32768]
	[door1Cycle 69] = [0 1 146 20 0 1 150 20 0 1 154 20 0 1 158 20 0 1 163 20 0 1 168 20 0 1 173 20 0 1 179 20 0 1 182 20 0 1 189 20 0 1 191 20 0 1 194 20 0 1 197 20 0 1 200 20 0 1 203 20 0 1 206 20 0 1 210 20 -32768]
)
(class mdlScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and saveBits (OneOf ((User curEvent?) type?) 1 4))
			(DoDisplay saveBits)
			(= saveBits (= seconds 0))
			(= cycles 1)
		)
	)
)

(instance rm355 of SQRoom
	(properties
		picture 355
	)
	
	(method (init &tmp [temp0 50])
		(HandsOff)
		(Load VIEW 355)
		(super init:)
		(door0 init:)
		(door1 init:)
		(carSFX init:)
		(music number: 355 vol: 127 loop: -1 flags: 1 playBed:)
		(self setScript: rmScript)
	)
)

(instance rmScript of mdlScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(carSFX play:)
				(ship init: z: 0 setCycle: MoveCycle @shipCycle self)
				(= ticks 60)
			)
			(1
				(door0 setCycle: MoveCycle @door0Cycle)
				(door1 setCycle: MoveCycle @door1Cycle)
			)
			(2 (= cycles 14))
			(3
				(carSFX fade:)
				(door0 setCycle: MoveCycle @door0Cycle -1)
				(door1 setCycle: MoveCycle @door1Cycle self -1)
			)
			(4
				(music fade:)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 803 IRISIN)
				(= seconds 2)
			)
			(5
				(door0 dispose:)
				(door1 dispose:)
				(= cycles 1)
			)
			(6
				(ShowStatus 12)
				(curRoom drawPic: 119 IRISOUT)
				(= cycles 1)
			)
			(7
				(music number: 48 loop: 1 vol: 127 playBed:)
				(narrator
					modeless: FALSE
					returnVal: 0
					nMsgType: 3
					modNum: 355
					say: 1 self 2 64 21 40 67 275 25 myTextColor7 26 myLowlightColor 27 1
				)
				(= seconds 3)
			)
			(8
				(narrator nMsgType: -1)
				(= cycles 2)
			)
			(9 (curRoom newRoom: 153))
		)
	)
)

(instance carSFX of Sound
	(properties
		number 109
	)
)

(instance door0 of Sq4Prop
	(properties
		x 111
		y 20
		view 355
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (cue)
		(super cue:)
		(self hide:)
	)
)

(instance door1 of Sq4Prop
	(properties
		x 146
		y 20
		view 355
		cel 1
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (cue)
		(super cue:)
		(self hide:)
	)
)

(instance ship of Sq4Actor
	(properties
		x 184
		y 187
		z 1000
		yStep 5
		view 355
		loop 1
		priority 5
		signal (| ignrHrz fixedLoop fixPriOn)
		xStep 5
	)
)
