;;; Sierra Script 1.0 - (do not remove this comment)
(script# 345)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use Sound)
(use Motion)
(use System)

(public
	rm345 0
)

(instance rm345 of SQRoom
	(properties
		picture 345
	)
	
	(method (init)
		(ShowStatus 10)
		(thoreenMouth init:)
		(thoreenEyes init:)
		(thoreenTalker init: 0 thoreenEyes thoreenMouth)
		(arm init:)
		(coil init:)
		(wizz init:)
		(wizzSfx init:)
		(armSFX init:)
		(self setScript: threatScript)
		(super init:)
	)
)

(instance threatScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(music hold: 0)
				(HandsOff)
				(= seconds 3)
			)
			(1
				(thoreenTalker
					say:
						3
						self
						2
						64
						2
						1
						25
						myTopBordColor
						26
						(VGAOrEGA myInsideColor myLowlightColor)
						27
						1
						67
						315
				)
			)
			(2
				(music pause: TRUE)
				(armSFX play:)
				(arm z: 0 setPri: 13 setCycle: BegLoop)
				(= cycles 30)
			)
			(3
				(armSFX dispose:)
				(music pause: 0)
				(wizzSfx play:)
				(= cycles 1)
			)
			(4
				(= start state)
				(coil z: 0 setPri: 14 setCel: 1)
				(wizz z: 0 setCycle: EndLoop self)
				(= cycles 5)
			)
			(5
				(coil setCel: 0)
				(wizz setCycle: BegLoop self)
			)
			(6
				(if (< (++ register) 10)
					(self init:)
				else
					(wizz dispose:)
					(wizzSfx dispose:)
					(= cycles 1)
				)
			)
			(7
				(thoreenTalker
					say:
						4
						self
						2
						64
						2
						1
						25
						myTopBordColor
						26
						(VGAOrEGA myInsideColor myLowlightColor)
						27
						1
						67
						315
				)
			)
			(8
				(thoreenTalker
					say:
						5
						self
						2
						64
						2
						1
						25
						myTopBordColor
						26
						(VGAOrEGA myInsideColor myLowlightColor)
						27
						1
						67
						315
				)
			)
			(9
				(thoreenTalker
					say:
						6
						self
						2
						64
						2
						1
						25
						myTopBordColor
						26
						(VGAOrEGA myInsideColor myLowlightColor)
						27
						1
						67
						315
				)
			)
			(10
				(self setScript: shockScript)
			)
		)
	)
)

(instance shockScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wizz dispose:)
				(curRoom overlay: 346)
				(roarSFX init: play:)
				(face init:)
				(arm z: -1000)
				(= seconds 4)
			)
			(1
				(music
					init:
					number: 2
					loop: -1
					vol: 127
					flags: mNOPAUSE
					playBed:
				)
				(= cycles 20)
			)
			(2
				(thoreenTalker dispose:)
				(curRoom newRoom: 335)
			)
		)
	)
)

(instance wizzSfx of Sound
	(properties
		number 157
		loop -1
	)
)

(instance roarSFX of Sound
	(properties
		number 828
	)
)

(instance armSFX of Sound
	(properties
		number 866
	)
)

(instance face of Sq4Prop
	(properties
		x 142
		y 88
		view 345
		loop 2
		priority 12
		signal (| ignrAct fixPriOn)
	)
)

(instance arm of Sq4Prop
	(properties
		x 109
		y 189
		z -1000
		view 345
		loop 3
		cel 3
		priority 13
	)
)

(instance coil of Sq4Prop
	(properties
		x 136
		y 148
		z -1000
		view 345
		loop 4
		cel 1
	)
)

(instance wizz of Sq4Prop
	(properties
		x 176
		y 86
		z -1000
		view 345
		loop 5
	)
)

(instance thoreenTalker of FaceTalker
	(properties
		modNum 322
		talkerNum THOREEN
	)
)

(instance thoreenEyes of Sq4Prop
	(properties
		x 138
		y 62
		view 1345
		priority 8
		signal (| ignrAct fixPriOn)
	)
)

(instance thoreenMouth of Sq4Prop
	(properties
		x 146
		y 87
		view 1345
		loop 1
		priority 7
		signal (| ignrAct fixPriOn)
	)
)
