;;; Sierra Script 1.0 - (do not remove this comment)
(script# 501)
(include sci.sh)
(use Main)
(use Scaler)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	theNukem 2
	theStairs 3
	theQuirk 4
	thePatrons1 5
	thePatrons2 6
	thePlants 7
	theLamp 10
	sVictimExit 11
	sVictimGuardExit 12
	sGlob1 13
	sGlob2 14
	smallmonk 15
	tinymonk1 16
	tinymonk2 17
	tinymonk3 18
	tinymonk4 19
	monkswim 20
	monkswim2 21
	guy1 22
	sGuys 24
	guy1Mouth 25
	droid 27
	sDroidExit 28
	theBar 29
	theTable 30
	theTeleporter 31
	theAlien 32
	theDetention 33
	theSprint 34
)

(local
	local0
	local1
	local2
	local3
)
(instance sVictimExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				((ScriptID 500 1) setCycle: End self)
			)
			(2
				((ScriptID 500 1)
					view: 508
					setLoop: 0
					cel: 0
					setScale: Scaler 80 80 157 109
					x: 182
					y: 136
					setPri: 13
				)
				(= cycles 1)
			)
			(3
				((ScriptID 500 1)
					setCycle: Fwd
					cycleSpeed: 6
					setPri: 11
					setMotion: MoveTo 214 129 self
				)
			)
			(4
				((ScriptID 500 1)
					setStep: 6 6
					setMotion: MoveTo 237 118 self
				)
			)
			(5
				((ScriptID 500 1) setLoop: 1 cel: 15 setCycle: 0)
				(= cycles 1)
			)
			(6 (self dispose:))
		)
	)
)

(instance sVictimGuardExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 500 2)
					view: 507
					setLoop: 11
					cel: 0
					x: 139
					y: 131
					setScale: Scaler 103 85 146 131
				)
				(= seconds 1)
			)
			(1
				((ScriptID 500 2)
					view: 511
					setLoop: 3
					cel: 0
					x: 139
					y: 131
					setPri: 13
					setCycle: Fwd
					setMotion: MoveTo 167 147 self
				)
			)
			(2
				((ScriptID 500 2)
					view: 511
					setLoop: 0
					cel: 0
					setPri: 13
					setMotion: MoveTo 340 147 self
				)
			)
			(3
				((ScriptID 500 2) dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sGlob1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(<
						(theGame detailLevel:)
						((ScriptID 500 3) detailLevel:)
					)
					((ScriptID 500 3) stopUpd:)
					(-- state)
				)
				(= seconds (Random 1 4))
			)
			(1
				((ScriptID 500 3) setMotion: MoveTo 188 2 self)
			)
			(2
				((ScriptID 500 3) setMotion: MoveTo 212 3 self)
			)
			(3
				((ScriptID 500 3) setMotion: MoveTo 188 83 self)
			)
			(4 (= state -1) (= cycles 2))
		)
	)
)

(instance sGlob2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(<
						(theGame detailLevel:)
						((ScriptID 500 4) detailLevel:)
					)
					((ScriptID 500 4) stopUpd:)
					(-- state)
				)
				(= seconds 1)
			)
			(1
				((ScriptID 500 4) setMotion: MoveTo 200 86 self)
			)
			(2 (= seconds (Random 1 4)))
			(3
				((ScriptID 500 4) setMotion: MoveTo 178 1 self)
			)
			(4
				((ScriptID 500 4) setMotion: MoveTo 210 1 self)
			)
			(5 (= state -1) (= cycles 2))
		)
	)
)

(instance sGuys of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(< (theGame detailLevel:) (guy1Mouth detailLevel:))
					(guy1Mouth stopUpd:)
					(-- state)
				else
					(guy1Mouth setCycle: Fwd init:)
				)
				(= cycles 1)
			)
			(1 (= seconds (Random 1 6)))
			(2
				(guy1Mouth setCycle: End self)
			)
			(3 (= seconds (Random 1 6)))
			(4 (= state -1) (= cycles 1))
		)
	)
)

(instance sDroidExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(droid setMotion: MoveTo 122 88 self)
			)
			(1
				(droid setPri: 3 setMotion: MoveTo 74 80 self)
			)
			(2
				(droid dispose:)
				(self dispose:)
			)
		)
	)
)

(instance droid of Actor
	(properties
		x 106
		y 96
		view 501
		loop 8
		priority 10
	)
	
	(method (init)
		(super init: &rest)
		(self
			setLoop: 8
			setCycle: Fwd
			cycleSpeed: 10
			setStep: 6 6
			setScale: Scaler 100 80 96 80
		)
	)
)

(instance guy1 of View
	(properties
		x 208
		y 140
		noun 1
		view 515
		loop 11
		priority 14
		signal $0010
	)
)

(instance guy1Mouth of Prop
	(properties
		x 216
		y 161
		noun 1
		view 515
		loop 12
		cel 2
		priority 14
		signal $4010
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 12)
	)
)

(instance smallmonk of Actor
	(properties
		x 107
		y 78
		view 504
		loop 12
		priority 15
		signal $4010
	)
	
	(method (init)
		(super init: &rest)
	)
)

(instance tinymonk1 of Actor
	(properties
		x -10
		view 504
		loop 12
		priority 12
		signal $4010
		illegalBits $0000
	)
	
	(method (init)
		(switch (Random 0 1)
			(0
				(super init: &rest)
				(self
					setLoop: 12
					x: -10
					y: (Random 10 180)
					setMotion: MoveTo 330 (Random 10 180) self
				)
			)
			(1
				(super init: &rest)
				(self
					setLoop: 12
					x: 330
					y: (Random 10 180)
					setMotion: MoveTo -10 (Random 10 180) self
				)
			)
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance tinymonk2 of Actor
	(properties
		x 107
		y 78
		view 504
		loop 13
		priority 12
		signal $4010
		illegalBits $0000
	)
	
	(method (init)
		(switch (Random 0 1)
			(0
				(super init: &rest)
				(self
					setLoop: 12
					x: -10
					y: (Random 0 180)
					setMotion: MoveTo 330 (Random 10 200) self
				)
			)
			(1
				(super init: &rest)
				(self
					setLoop: 12
					x: 330
					y: (Random 0 200)
					setMotion: MoveTo -10 (Random 0 200) self
				)
			)
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance tinymonk3 of Actor
	(properties
		x 107
		y 78
		view 504
		loop 13
		priority 12
		signal $4010
		illegalBits $0000
	)
	
	(method (init)
		(switch (Random 0 1)
			(0
				(super init: &rest)
				(self
					setLoop: 12
					x: -10
					y: (Random 0 180)
					setMotion: MoveTo 330 (Random 10 200) self
				)
			)
			(1
				(super init: &rest)
				(self
					setLoop: 12
					x: 330
					y: (Random 0 200)
					setMotion: MoveTo -10 (Random 0 200) self
				)
			)
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance tinymonk4 of Actor
	(properties
		x 107
		y 78
		view 504
		loop 13
		priority 12
		signal $4010
		illegalBits $0000
	)
	
	(method (init)
		(switch (Random 0 1)
			(0
				(super init: &rest)
				(self
					setLoop: 12
					x: -10
					y: (Random 0 180)
					setMotion: MoveTo 330 (Random 10 200) self
				)
			)
			(1
				(super init: &rest)
				(self
					setLoop: 12
					x: 330
					y: (Random 0 200)
					setMotion: MoveTo -10 (Random 0 200) self
				)
			)
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance monkswim of Actor
	(properties
		noun 19
		view 505
		cel 4
		priority 13
		signal $4810
		illegalBits $0000
	)
	
	(method (init)
		(switch (Random 0 3)
			(0
				(= local1 200)
				(= local0 (Random 0 100))
				(= local3 0)
				(= local2 (Random 200 320))
				(self
					setLoop: 0
					x: local0
					y: local1
					setPri: 13
					setMotion: MoveTo local2 local3 self
				)
			)
			(1
				(= local1 200)
				(= local0 (Random 220 320))
				(= local3 0)
				(= local2 (Random 0 100))
				(self
					setLoop: 1
					x: local0
					y: local1
					setPri: 13
					setMotion: MoveTo local2 local3 self
				)
			)
			(2
				(= local1 0)
				(= local0 (Random 0 100))
				(= local3 200)
				(= local2 (Random 200 320))
				(self
					setLoop: 2
					x: local0
					y: local1
					setPri: 13
					setMotion: MoveTo local2 local3 self
				)
			)
			(3
				(= local1 0)
				(= local0 (Random 220 320))
				(= local3 200)
				(= local2 (Random 0 100))
				(self
					setLoop: 3
					x: local0
					y: local1
					setPri: 13
					setMotion: MoveTo local2 local3 self
				)
			)
		)
		(super init: &rest)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance monkswim2 of Actor
	(properties
		noun 19
		view 505
		cel 4
		priority 13
		signal $4810
		illegalBits $0000
	)
	
	(method (init)
		(switch (Random 0 3)
			(0
				(= local1 200)
				(= local0 (Random 0 100))
				(= local3 0)
				(= local2 (Random 200 320))
				(self
					setLoop: 0
					x: local0
					y: local1
					setPri: 13
					setMotion: MoveTo local2 local3 self
				)
			)
			(1
				(= local1 200)
				(= local0 (Random 220 320))
				(= local3 0)
				(= local2 (Random 0 100))
				(self
					setLoop: 1
					x: local0
					y: local1
					setPri: 13
					setMotion: MoveTo local2 local3 self
				)
			)
			(2
				(= local1 0)
				(= local0 (Random 0 100))
				(= local3 200)
				(= local2 (Random 200 320))
				(self
					setLoop: 2
					x: local0
					y: local1
					setPri: 13
					setMotion: MoveTo local2 local3 self
				)
			)
			(3
				(= local1 0)
				(= local0 (Random 220 320))
				(= local3 200)
				(= local2 (Random 0 100))
				(self
					setLoop: 3
					x: local0
					y: local1
					setPri: 13
					setMotion: MoveTo local2 local3 self
				)
			)
		)
		(super init: &rest)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance theNukem of Feature
	(properties
		x 70
		y 12
		noun 10
		modNum 500
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 10 theVerb 0 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theStairs of Feature
	(properties
		x 10
		y 128
		noun 22
		modNum 500
		onMeCheck $0010
		approachX 40
		approachY 150
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(switch global167
					(0
						(curRoom setScript: (ScriptID 500 5))
					)
					(1
						(curRoom setScript: (ScriptID 500 5))
					)
					(2
						(curRoom setScript: (ScriptID 500 11))
					)
				)
			)
			(4
				(switch global167
					(0
						(curRoom setScript: (ScriptID 500 5))
					)
					(1
						(curRoom setScript: (ScriptID 500 5))
					)
					(2
						(curRoom setScript: (ScriptID 500 11))
					)
				)
			)
			(1
				(messager say: 22 theVerb 0 0 500)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theQuirk of Feature
	(properties
		x 245
		y 10
		noun 15
		nsTop 2
		nsLeft 245
		nsBottom 15
		nsRight 261
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 53) (messager say: 15 theVerb 0 0 500))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theAlien of Feature
	(properties
		x 245
		y 190
		noun 2
		modNum 500
		nsTop 2
		nsLeft 265
		nsBottom 15
		nsRight 291
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 53) (messager say: 2 theVerb 0 0 500))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance thePatrons1 of Feature
	(properties
		x 10
		y 12
		noun 11
		modNum 500
		onMeCheck $0200
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (not (Btst 54)) (messager say: 11 theVerb 0 0 500))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance thePatrons2 of Feature
	(properties
		x 150
		y 50
		noun 12
		modNum 500
		onMeCheck $0400
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (not (Btst 54)) (messager say: 12 theVerb 0 0 500))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance thePlants of Feature
	(properties
		x 180
		y 12
		noun 13
		onMeCheck $0100
	)
)

(instance theLamp of Feature
	(properties
		x 76
		y 108
		noun 8
		modNum 500
		nsTop 22
		nsLeft 168
		nsBottom 89
		nsRight 219
	)
)

(instance theBar of Feature
	(properties
		x 160
		y 90
		noun 4
		modNum 500
		nsTop 95
		nsLeft 157
		nsBottom 110
		nsRight 240
	)
)

(instance theTeleporter of Feature
	(properties
		x 260
		y 95
		noun 24
		modNum 500
		onMeCheck $0040
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (Btst 62)
					(curRoom setScript: (ScriptID 500 8))
				else
					(curRoom setScript: (ScriptID 500 7))
				)
			)
			(4
				(if (Btst 62)
					(curRoom setScript: (ScriptID 500 8))
				else
					(curRoom setScript: (ScriptID 500 7))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theTable of Feature
	(properties
		x 76
		y 108
		noun 23
		modNum 500
		nsTop 85
		nsLeft 52
		nsBottom 124
		nsRight 102
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (and (not (Btst 62)) (not (Btst 54)))
					(curRoom setScript: (ScriptID 500 9))
				)
				(if (and (Btst 62) (not (Btst 54)))
					(curRoom newRoom: 520)
				)
			)
			(4
				(if (and (not (Btst 62)) (not (Btst 54)))
					(curRoom setScript: (ScriptID 500 9))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theDetention of Feature
	(properties
		x 310
		y 180
		nsTop 55
		nsLeft 305
		nsBottom 162
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(switch global167
					(1
						(curRoom setScript: (ScriptID 500 12))
					)
					(2
						(curRoom setScript: (ScriptID 500 12))
					)
					(0
						(curRoom setScript: (ScriptID 500 12))
					)
				)
			)
			(4
				(switch global167
					(1
						(curRoom setScript: (ScriptID 500 12))
					)
					(2
						(curRoom setScript: (ScriptID 500 12))
					)
					(0
						(curRoom setScript: (ScriptID 500 12))
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theSprint of Feature
	(properties
		x 310
		y 100
		noun 21
		modNum 500
		nsTop 70
		nsLeft 274
		nsBottom 120
		nsRight 319
	)
)
