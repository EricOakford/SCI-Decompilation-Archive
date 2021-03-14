;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
(include game.sh) (include "330.shm")
(use Main)
(use TellerIcon)
(use OccasionalCycle)
(use Talker)
(use Feature)
(use LoadMany)
(use Timer)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm330 0
	rajahTalker 1
)

(local
	local0
	flame2Cel
	local2
	local3
	local4
	wasRude
	local6
	theGameTime
	local8 = [0 -9 -26 -45 -4 -8 -28 -32 -33 -40 -11 -12 -34 -39 -5 -13 -29 -35 -38 999]
	local28 = [0 -16 -6 -19 -24 -17 -14 -15 -30 -44 999]
	local39 = [0 11 18 999]
	[local43 4]
	[local47 5]
	local52 = [0 -14 999]
)
(instance rm330 of Room
	(properties
		noun N_ROOM
		picture 330
		vanishingY 1
	)
	
	(method (init)
		(LoadMany RES_SCRIPT MESSAGER TALKER)
		(LoadMany RES_VIEW 961)
		(= [local43 0] @local8)
		(= [local47 0] @local28)
		(= [local47 1] @local39)
		(ego noun: N_EGO_TELL)
		(egoPic init: actions: tell)
		(tell init: ego @local8 @local43)
		(rajahTeller init: rajah @local28 @local47 @local52)
		(rajah init: setCycle: OccasionalCycle self 5 70 180)
		(rajahRightArm
			init:
			setCycle: OccasionalCycle self 1 70 180
		)
		(localRakeesh init:)
		(cheeseCake1
			init:
			setCycle: OccasionalCycle self 1 30 100
		)
		(cheeseCake2 init:)
		(flame1 init: setCycle: Forward)
		(flame2 init: setCycle: OccasionalCycle self 1 30 80)
		(lightglobe init:)
		(spittoon init:)
		(hieroglyphics init:)
		(incenseburner init:)
		(walkHandler addToFront: self)
		(super init:)
		(cSound number: 330 setLoop: -1 play: 127)
		(if (Btst fMetRajah)
			(curRoom setScript: situationTwo)
		else
			(Bset fMetRajah)
			(curRoom setScript: situationOne)
		)
	)
	
	(method (dispose)
		((ego actions?) dispose:)
		(ego actions: 0)
		(walkHandler delete: self)
		(timers
			eachElementDo: #dispose
			eachElementDo: #delete
			release:
		)
		(UnLoad RES_VIEW 961)
		(LoadMany FALSE RAKEESH_TALKER)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_WALK)
			(curRoom newRoom: 320)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance situationOne of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(DontMove)
				(= cycles 5)
			)
			(1
				(= local3 1)
				(messager say: N_RAJAH V_TELL C_INTRO 0 self)
			)
			(2
				((Timer new:) setReal: self 10)
			)
			(3
				(= local3 2)
				(messager say: N_RAJAH V_TELL C_QUESTION_EGO 1 self)
			)
			(4
				(messager say: N_RAJAH V_TELL C_QUESTION_EGO (+ heroType 2) self)
			)
			(5
				((Timer new:) setReal: self 10)
			)
			(6
				(if (& local3 $0400)
					((Timer new:) setReal: self 5)
				else
					(self cue:)
				)
			)
			(7
				(if register
					(= register 0)
				else
					(= wasRude TRUE)
					(ego addHonor: -10)
				)
				(= local3 4)
				(messager say: N_RAJAH V_TELL C_PEACE 0 self)
			)
			(8
				((Timer new:) setReal: self 5)
			)
			(9
				(= local3 8)
				(messager say: N_RAJAH V_TELL C_REESHAKA 0 self)
			)
			(10
				((Timer new:) setReal: self 10)
			)
			(11
				(cond 
					((< local6 1)
						(messager say: N_RAJAH V_TELL C_RUDE1 0 self)
					)
					((== local6 3)
						(messager say: N_RAJAH V_TELL C_RUDE2 0 self)
					)
					(else
						(self cue:)
					)
				)
			)
			(12
				(if register
					(= register 0)
				else
					(= wasRude TRUE)
					(ego addHonor: -10)
				)
				(= local3 16)
				(theIconBar enable: ICON_WALK)
				(messager say: N_RAJAH V_TELL C_DISMISSED 1 self)
			)
			(13
				((Timer new:) setReal: self 10)
			)
			(14
				(if (and register (not local4))
					(= register 0)
					(ego addHonor: 10)
					(self cue:)
				else
					(= wasRude TRUE)
					(ego addHonor: -10)
					(messager say: N_RAJAH V_DOIT C_KICKED_OUT 0 self)
				)
			)
			(15
				(if (== wasRude 0)
					(ego addHonor: 25)
					(ego solvePuzzle: fPoliteToRajahFirst 2 puzzlePALADIN)
				)
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance situationTwo of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(DontMove)
				(= cycles 5)
			)
			(1
				(= local3 64)
				(messager say: 3 2 28 0 self)
			)
			(2
				((Timer new:) setReal: self 10)
			)
			(3
				(= register 0)
				(= local3 128)
				(messager say: N_RAJAH V_TELL 31 0 self)
			)
			(4
				((Timer new:) setReal: self 10)
			)
			(5
				(if register
					(= register 0)
					(self cue:)
				else
					(ego addHonor: -10)
					(messager say: N_RAJAH V_TELL 36 0 self)
				)
			)
			(6
				(= local3 256)
				(messager say: N_RAJAH V_TELL 37 0 self)
			)
			(7
				((Timer new:) setReal: self 10)
			)
			(8
				(cond 
					((< local6 1) (ego addHonor: -10) (messager say: N_RAJAH V_TELL 42 0 self))
					((== local6 3) (messager say: N_RAJAH V_TELL 22 0 self))
					(else (self cue:))
				)
			)
			(9
				(= local3 512)
				(messager say: N_RAJAH V_TELL 43 0 self)
			)
			(10
				((Timer new:) setReal: self 5)
			)
			(11
				(if (and register (not local4))
					(= register 0)
					(ego addHonor: 10)
					(self cue:)
				else
					(ego addHonor: -10)
					(messager say: 1 6 49 0 self)
				)
			)
			(12
				(HandsOff)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 0)
				(= cycles 30)
			)
			(13
				(messager say: N_RAJAH V_TELL 48 1 self)
			)
			(14
				(= Day 3)
				(= Clock 1300)
				(curRoom newRoom: 150)
			)
		)
	)
)

(instance rajahTeller of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-6
				(& local3 $0007)
				-16
				(& local3 $0108)
				-17
				(& local3 $0108)
				-14
				(& local3 $0108)
				-15
				(& local3 $0108)
				-19
				(& local3 $0010)
				-24
				(& local3 $0020)
				-30
				(& local3 $00c0)
				-44
				(& local3 $0200)
		)
	)
	
	(method (doChild param1 &tmp temp0 temp1)
		(cond 
			((== param1 -6) (= wasRude 1) (ego addHonor: -10) (= temp0 1))
			(
			(or (== param1 -16) (== param1 -17) (== param1 -15))
				(if (> local6 2)
					(= temp0 1)
					(= temp1 1)
				else
					(++ local6)
				)
			)
			((== param1 -14)
				(super doChild: param1)
				(if (> local6 2)
					(= temp1 1)
					(= temp0 1)
				else
					(++ local6)
				)
			)
			((== param1 -19) (= wasRude 1) (ego addHonor: -10) (= local3 32))
			((== param1 -24) (= wasRude 1) (ego addHonor: -10) (= temp0 1))
			((== param1 -30) (= wasRude 1) (ego addHonor: -10) (= temp0 1))
			((== param1 -44)
				(ego addHonor: -10)
				(if (not local4)
					(= local4 1)
				else
					(= temp0 1)
					(= temp1 1)
				)
			)
		)
		(if (== temp0 1)
			((curRoom script?) register: 1 cycles: 2)
			(if (IsObject ((curRoom script?) timer?))
				(((curRoom script?) timer?) dispose:)
			)
			(= temp0 0)
		)
		(return (if (== temp1 1) (= temp1 0) else (return 1)))
	)
)

(instance rajah of Prop
	(properties
		x 164
		y 104
		noun 1
		onMeCheck $0002
		view 332
		loop 2
		signal $0010
	)
	
	(method (onMe theObjOrX theY &tmp temp0 temp1)
		(if (IsObject theObjOrX)
			(= temp0 (theObjOrX x?))
			(= temp1 (theObjOrX y?))
		else
			(= temp0 theObjOrX)
			(= temp1 theY)
		)
		(return (& onMeCheck (OnControl 4 temp0 temp1)))
	)
)

(instance egoPic of Feature
	(properties
		x 5
		y 5
		noun 2
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(ego doVerb: theVerb)
	)
)

(instance rajahRightArm of Prop
	(properties
		x 201
		y 78
		view 332
		loop 1
	)
	
	(method (doVerb theVerb)
		(rajah doVerb: theVerb)
	)
)

(instance localRakeesh of Feature
	(properties
		x 18
		y 189
		onMeCheck $0004
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(2
					(cond 
						((& local3 $0007)
							(= wasRude 1)
							(ego addHonor: -10)
							(= temp0 1)
							(messager say: 3 5 6)
						)
						((& local3 $0008)
							(= wasRude 1)
							(ego addHonor: -10)
							(= local6 999)
							(= temp0 1)
							(messager say: 3 5 19)
						)
						((& local3 $0010)
							(= wasRude 1)
							(ego addHonor: -10)
							(= temp0 0)
							(messager say: 3 5 24)
							(= local3 32)
						)
						((& local3 $0020) (= local4 1) (= temp0 1) (messager say: 3 5 25))
						((or (& local3 $0040) (& local3 $0080)) (ego addHonor: -10) (= temp0 1) (messager say: 3 5 30))
						((& local3 $0100)
							(= local6 999)
							(ego addHonor: -10)
							(= temp0 1)
							(messager say: 3 5 30)
						)
						((& local3 $0300)
							(if (not local4)
								(= local4 1)
								(ego addHonor: -10)
								(= temp0 0)
								(messager say: N_RAJAH V_TELL 27)
							else
								(= temp0 1)
							)
						)
					)
					(if temp0
						((curRoom script?) register: 1 cycles: 2)
						(if (IsObject ((curRoom script?) timer?))
							(((curRoom script?) timer?) dispose:)
						)
					)
					(return 1)
				)
				(1 (messager say: 3 1 0))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance cheeseCake1 of Prop
	(properties
		x 116
		y 91
		noun 10
		onMeCheck $0010
		view 331
		loop 1
		priority 5
		signal $0010
		cycleSpeed 12
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(2
					(cond 
						((& local3 $0007)
							(= wasRude 1)
							(ego addHonor: -10)
							(= temp0 1)
							(messager say: 4 5 6)
						)
						((& local3 $0008)
							(= local6 999)
							(= wasRude 1)
							(ego addHonor: -10)
							(= temp0 1)
							(messager say: 4 5 19)
						)
						((& local3 $0010)
							(= wasRude 1)
							(ego addHonor: -10)
							(= temp0 0)
							(messager say: 4 5 24)
							(= local3 32)
						)
						((& local3 $0020) (= temp0 1) (= local4 1) (messager say: 4 5 25))
						((or (& local3 $0040) (& local3 $0080)) (ego addHonor: -10) (= temp0 1) (messager say: 4 5 30))
						((& local3 $0100)
							(= local6 999)
							(ego addHonor: -10)
							(= temp0 1)
							(messager say: 4 5 30)
						)
						((& local3 $0200)
							(if (not local4)
								(= local4 1)
								(= temp0 0)
								(ego addHonor: -10)
								(messager say: N_RAJAH V_TELL 27)
							else
								(= temp0 1)
							)
						)
					)
					(if temp0
						((curRoom script?) register: 1 cycles: 2)
						(if (IsObject ((curRoom script?) timer?))
							(((curRoom script?) timer?) dispose:)
						)
					)
					(return 1)
				)
				(1 (messager say: 4 1 0))
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (onMe theObjOrX theY &tmp temp0 temp1)
		(if (IsObject theObjOrX)
			(= temp0 (theObjOrX x?))
			(= temp1 (theObjOrX y?))
		else
			(= temp0 theObjOrX)
			(= temp1 theY)
		)
		(return (& onMeCheck (OnControl 4 temp0 temp1)))
	)
)

(instance cheeseCake2 of Feature
	(properties
		x 238
		y 170
		onMeCheck $0020
	)
	
	(method (doVerb)
		(cheeseCake1 doVerb: &rest)
	)
	
	(method (onMe theObjOrX theY &tmp temp0 temp1)
		(if (IsObject theObjOrX)
			(= temp0 (theObjOrX x?))
			(= temp1 (theObjOrX y?))
		else
			(= temp0 theObjOrX)
			(= temp1 theY)
		)
		(return (& onMeCheck (OnControl 4 temp0 temp1)))
	)
)

(instance tell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-4
				(& local3 $0003)
				-5
				(& local3 $0007)
				-9
				(& local3 $0002)
				-8
				(& local3 $0400)
				-11
				(& local3 $0004)
				-12
				(& local3 $0008)
				-13
				(& local3 $0008)
				-26
				(& local3 $0030)
				-28
				(& local3 $0040)
				-32
				(& local3 $0080)
				-33
				(& local3 $0080)
				-29
				(& local3 $0040)
				-34
				(& local3 $0080)
				-35
				(& local3 $0080)
				-40
				(& local3 $0100)
				-39
				(& local3 $0100)
				-38
				(& local3 $0100)
				-45
				(& local3 $0200)
		)
	)
	
	(method (doChild param1 &tmp temp0 temp1)
		(cond 
			((== param1 -4)
				(if (& local3 $0002)
					(= local3 1024)
				else
					(ego addHonor: 5)
				)
				(= temp0 1)
			)
			((== param1 -5) (= wasRude 1) (ego addHonor: -10) (= temp0 1))
			((== param1 -9)
				(messager say: 2 5 9 1)
				(if (<= (ego trySkill: 13 120) 0)
					(messager say: 2 5 9 2)
				else
					(ego addHonor: 5)
					(messager say: 2 5 9 3)
				)
				(= temp1 1)
				(= temp0 1)
			)
			((== param1 -8) (= temp0 1))
			((== param1 -11) (ego addHonor: 20 solvePuzzle: fPoliteToRajahSecond 2 puzzlePALADIN) (= temp0 1))
			((== param1 -12)
				(= local6 999)
				(messager say: 2 5 12 1)
				(if (< [egoStats 13] 120)
					(messager say: 2 5 12 2)
				else
					(messager say: 2 5 12 3)
					(ego addHonor: 20)
				)
				(= temp1 1)
				(= temp0 1)
			)
			((== param1 -13)
				(= local6 999)
				(if (< [egoStats 13] 120)
					(messager say: 2 5 13 2)
				else
					(messager say: 2 5 13 3)
				)
				(= temp1 1)
				(= temp0 1)
			)
			((== param1 -26) (ego addHonor: 3) (= temp0 1))
			((== param1 -28) (ego addHonor: 5) (= temp0 1))
			((== param1 -29) (= wasRude 1) (ego addHonor: -10) (= temp0 1))
			((== param1 -32) (= temp0 1))
			((== param1 -35) (= wasRude 1) (ego addHonor: -10) (= temp0 1))
			((== param1 -34) (ego addHonor: 20) (= temp0 1))
			((== param1 -33) (ego addHonor: 20) (= temp0 1))
			((== param1 -40) (= local6 999) (= temp0 1))
			((== param1 -38) (= local6 999) (ego addHonor: -10) (= temp0 1))
			((== param1 -39) (= local6 999) (ego addHonor: 20) (= temp0 1))
			((== param1 -45) (ego addHonor: 3) (= temp0 1))
		)
		(if temp0
			((curRoom script?) register: 1 cycles: 2)
			(if (IsObject ((curRoom script?) timer?))
				(((curRoom script?) timer?) dispose:)
			)
			(= temp0 0)
		)
		(return (if (== temp1 1) (= temp1 0) else (return 1)))
	)
)

(instance flame1 of Prop
	(properties
		x 45
		y 21
		view 330
		priority 14
		signal $0010
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(lightglobe doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance flame2 of Prop
	(properties
		x 286
		y 53
		view 330
		loop 1
		priority 14
		signal fixPriOn
	)
)

(instance rajahTalker of Talker
	(properties
		x 226
		y 14
		view 339
		loop 1
		talkWidth 260
		color 41
		back 57
		textX -195
		textY 150
	)
	
	(method (init)
		(super init: rajahBust rajahEyes rajahMouth &rest)
		(= theGameTime 0)
	)
	
	(method (doit)
		(if (not (mod (++ theGameTime) 40))
			(if (> (++ local0) 7) (= local0 0))
			(cond 
				(local2
					(if (> (++ flame2Cel) 10)
						(= flame2Cel 0)
						(= local2 0)
					)
				)
				((!= (flame2 cel?) 0) (= local2 1) (= flame2Cel (flame2 cel?)))
				((not (Random 0 19)) (= local2 1) (= flame2Cel 0))
			)
			(flame1 cel: local0)
			(flame2 cel: flame2Cel)
			(Animate (cast elements?) FALSE)
		)
		(super doit:)
	)
)

(instance rajahMouth of Prop
	(properties
		view 339
	)
)

(instance rajahBust of Prop
	(properties
		view 339
		loop 3
	)
)

(instance rajahEyes of Prop
	(properties
		view 339
		loop 2
	)
)

(instance lightglobe of Feature
	(properties
		x 43
		y 18
		noun 6
		nsTop 5
		nsLeft 28
		nsBottom 32
		nsRight 58
	)
)

(instance spittoon of Feature
	(properties
		x 191
		y 153
		noun 7
		nsTop 143
		nsLeft 171
		nsBottom 164
		nsRight 211
	)
)

(instance hieroglyphics of Feature
	(properties
		x 76
		y 33
		noun 8
		nsLeft 65
		nsBottom 66
		nsRight 88
	)
)

(instance incenseburner of Feature
	(properties
		x 292
		y 58
		noun 9
		nsTop 41
		nsLeft 278
		nsBottom 75
		nsRight 307
	)
)
