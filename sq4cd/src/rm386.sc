;;; Sierra Script 1.0 - (do not remove this comment)
(script# 386)
(include game.sh)
(use Main)
(use mall)
(use SQRoom)
(use Sq4Dialog)
(use Sq4Narrator)
(use Sq4Feature)
(use Polygon)
(use Window)
(use System)

(public
	rm386 0
)

(local
	local0
	oldWindow
	[pigPolyPts 22] = [268 99 246 130 86 129 61 105 61 90 110 61 118 32 127 16 162 5 206 27 214 66]
)


(procedure (JobPrint &tmp ret [str 100] [noBuf 30] [yesBuf 30])
	(Message MsgGet 386 24 0 1 1 @str)
	(Message MsgGet 386 97 0 1 1 @noBuf)
	(Message MsgGet 386 97 0 2 1 @yesBuf)
	(= ret
		(PrintD @str
			#at 100 100
			#new
			#button @noBuf 0
			#new
			#button @yesBuf 1
		)
	)
)

(instance rm386 of SQRoom
	(properties
		picture 386
		style FADEOUT
	)
	
	(method (init)
		(Load VIEW 386)
		(Load SOUND 4)
		(= oldWindow systemWindow)
		(HandsOff)
		(bottomCigar init:)
		(redStuff init:)
		(yellowStuff init:)
		(microwave init:)
		(theMouth init:)
		(arm init:)
		(music number: 0 stop:)
		(globalSound vol: 127 changeState:)
		(thePig init:)
		(pigPoly points: @pigPolyPts size: 11)
		(theRoom init:)
		(self setScript: talkScript 0 0)
		(super init:)
		(self setRegions: MALL)
		(ego setCycle: 0)
		(tPig init: 0 0 theMouth)
	)
	
	(method (newRoom newRoomNumber)
		(if (== newRoomNumber 387) (globalSound fade:))
		(super newRoom: newRoomNumber)
	)
)

(instance sJob of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(arm setCel: 0)
				(= seconds 2)
			)
			(1
				(theMouth setLoop: 0 posn: 155 39)
				(arm setCel: 2)
				(= seconds 1)
			)
			(2
				(theMouth setLoop: 0 posn: 155 39)
				(tPig
					modNum: 386
					talkerNum: PIGHEAD
					say: 1 self 2 64 5 150 67 310 27 1
				)
			)
			(3
				(switch (JobPrint)
					(0 (curRoom newRoom: 385))
				)
				(= cycles 2)
			)
			(4
				(if (== (ego view?) 373)
					(tRogette say: 2 self)
				else
					(= cycles 2)
				)
			)
			(5 (client setScript: 0))
		)
	)
)

(instance theRoom of Sq4Feature
	(properties
		x 160
		y 100
		nsBottom 200
		nsRight 320
		lookStr 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK (curRoom newRoom: 385))
			(V_SMELL (SQ4Print 386 0))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance thePig of Sq4Feature
	(properties
		x 160
		y 100
		sightAngle 180
		lookStr 2
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: pigPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 2))
			(V_TALK
				(if (not (curRoom script?))
					(HandsOff)
					(talkScript register: (+ (talkScript register?) 1))
					(curRoom setScript: talkScript)
				)
			)
			(V_BUCKAZOID (narrator say: 3))
			(V_TASTE (narrator say: 4))
			(V_SMELL (narrator say: 5))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pigPoly of Polygon
	(properties)
)

(instance arm of Sq4Prop
	(properties
		x 94
		y 134
		sightAngle 180
		view 386
		loop 2
		cel 1
		priority 2
		signal (| fixedLoop fixPriOn)
		lookStr 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 2))
			(V_TALK
				(if (not (curRoom script?))
					(HandsOff)
					(talkScript register: (+ (talkScript register?) 1))
					(curRoom setScript: talkScript)
				)
			)
			(V_BUCKAZOID (narrator say: 3))
			(V_TASTE (narrator say: 4))
			(V_SMELL (narrator say: 5))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theMouth of Sq4Prop
	(properties
		x 158
		y 63
		view 1386
		loop 1
		priority 1
		signal (| fixedLoop fixPriOn)
	)
	
	(method (doVerb)
		(arm doVerb:)
	)
)

(instance talkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCycle: 0)
				(if register (= cycles 1) else (= seconds 1))
			)
			(1
				(switch register
					(0 (= cycles 1))
					(1
						(if (== (ego view?) 373)
							(tRogette modNum: 388 say: 1 self)
						else
							(tRog modNum: 388 say: 1 self)
						)
					)
					(2 (self setScript: sJob self))
				)
			)
			(2
				(arm setCel: 0)
				(theMouth setLoop: 0 posn: 155 39)
				(= seconds 2)
			)
			(3
				(switch register
					(0
						(if (== (ego view?) 373)
							(tPig modNum: 388 say: 2 self 2 64 5 150 67 310 27 1)
						else
							(tPig say: 3 self 2 64 5 150 67 310 27 1)
						)
						(arm setCel: 2)
					)
					(1
						(tPig
							modNum:
								(if
								(and (Btst fMonolithBurgerFired) (not (OneOf (ego view?) 373 374)))
									curRoomNum
								else
									388
								)
							say:
								(if
								(and (Btst fMonolithBurgerFired) (not (OneOf (ego view?) 373 374)))
									4
								else
									10
								)
								self
								2
								64
								5
								150
								67
								310
								27
								1
						)
						(arm setCel: 2)
					)
					(2
						(arm setCel: 2)
						(cond 
							((and (>= monolithBurgerEarnings 34) (!= (ego view?) 373)) (tPig say: 6 self 2 64 5 150 67 310 27 1))
							((== (ego view?) 373) (tPig modNum: 388 say: 7 self 2 64 5 150 67 310 27 1))
							((Btst fMonolithBurgerFired) (tPig say: 8 self 2 64 5 150 67 310 27 1))
							(else (tPig modNum: 388 say: 9 self 2 64 5 150 67 310 27 1))
						)
						(HandsOff)
						(cond 
							((and (>= monolithBurgerEarnings 34) (!= (ego view?) 373)) (tPig say: 6 self 2 64 5 150 67 310 27 1))
							((== (ego view?) 373) (tPig modNum: 388 say: 7 self 2 64 5 150 67 310 27 1))
							((Btst fMonolithBurgerFired) (tPig say: 8 self 2 64 5 150 67 310 27 1))
							(else (tPig say: 9 self 2 64 5 150 67 310 27 1))
						)
						(arm setCel: 2)
					)
				)
			)
			(4
				(if
					(and
						(>= register 2)
						(>= monolithBurgerEarnings 34)
						(!= (ego view?) 373)
					)
					(curRoom newRoom: 385)
				else
					(= cycles 2)
				)
			)
			((arm setCel: 0) (= cycles 5))
			((arm setCel: 1) (= cycles 5))
			(5
				(arm setCel: 0)
				(if (== register 2)
					(cond 
						((== (ego view?) 373) (tRogette modNum: 388 say: 4 self))
						((Btst fMonolithBurgerFired) (tRog say: 5 self))
						(else (tRog say: 6 self))
					)
				else
					(= seconds 2)
				)
			)
			(6
				(if (== register 2) (arm setCel: 0))
				(theMouth setLoop: 1 cel: 0 posn: 158 63)
				(if (< register 2)
					(HandsOn)
					(user canControl: FALSE)
					(theIconBar disable: ICON_WALK ICON_DO)
				)
				(if (== register 2) (= cycles 6) else (self dispose:))
			)
			(7
				(arm setCel: 1)
				(if (== (ego view?) 373)
					(curRoom newRoom: 385)
				else
					(curRoom setScript: warningScript)
				)
			)
		)
	)
)

(instance warningScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(cast eachElementDo: #hide)
				(curRoom drawPic: 803)
				(= seconds 2)
			)
			(2
				(theIconBar curIcon: (theIconBar at: 2))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(theIconBar disable:)
				(= systemWindow BlackWindow)
				(BlackWindow color: myTextColor back: myLowlightColor)
				(switch
					(narratorWarning
						x: 60
						y: 36
						say: 6 0 0 64 (narratorWarning x?) (narratorWarning y?)
					)
					(0
						(if
							(and
								(< monolithBurgerEarnings 34)
								(not (proc700_3 (ScriptID MALL 0) 680 4))
								(not (Btst fFlag29))
							)
							(ego get: iBuckazoid)
							(= buckazoids (+ buckazoids (- 34 monolithBurgerEarnings)))
							(= monolithBurgerEarnings 34)
							(mall rFlag4: (| (mall rFlag4?) $0001))
							(SolvePuzzle fSkippedBurgerMinigame -3)
							(narrator say: 7 self)
						else
							(narrator say: 8 self)
						)
						(= cycles 1)
					)
					(1
						(= local0 1)
						(SolvePuzzle fWorkedAtMonolithBurger 3)
						(= cycles 1)
					)
				)
			)
			(3
				(= systemWindow oldWindow)
				(theIconBar enable:)
				(= cycles 2)
			)
			(4
				(if local0
					(curRoom newRoom: 387)
				else
					(curRoom newRoom: 385)
				)
			)
		)
	)
)

(instance BlackWindow of SysWindow
	(properties)
)

(instance redStuff of Sq4Feature
	(properties
		x 306
		y 105
		nsTop 75
		nsLeft 294
		nsBottom 135
		nsRight 318
		sightAngle 180
		lookStr 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE (narrator say: 10))
			(V_SMELL (narrator say: 11))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance yellowStuff of Sq4Feature
	(properties
		x 282
		y 104
		nsTop 75
		nsLeft 272
		nsBottom 134
		nsRight 293
		sightAngle 180
		lookStr 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE (narrator say: 13))
			(V_SMELL (narrator say: 11))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance microwave of Sq4Feature
	(properties
		x 24
		y 106
		nsTop 84
		nsBottom 128
		nsRight 49
		sightAngle 180
		lookStr 14
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 15))
			(V_SMELL (narrator say: 16))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bottomCigar of Sq4Feature
	(properties
		x 93
		y 150
		nsTop 93
		nsLeft 79
		nsBottom 110
		nsRight 108
		sightAngle 180
		lookStr 14
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK)
			(V_DO (narrator say: 17))
			(V_SMELL (narrator say: 18))
			(V_TASTE
				(if (== (ego view?) 373)
					(tRogette say: 7)
				else
					(tRog say: 7)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance narratorWarning of Sq4Narrator
	(properties
		noun NARRATOR
		modNum 386
		modeless TRUE
		nMsgType 3
	)
	
	(method (dispose &tmp temp0)
		(if returnVal (DoDisplay returnVal))
		(super dispose: &rest)
	)
	
	(method (say theString &tmp ret [str 100] [yesBuf 30] [noBuf 30])
		(super say: theString &rest)
		(Message MsgGet 386 99 0 theString 1 @str)
		(Message MsgGet 386 97 0 3 1 @yesBuf)
		(Message MsgGet 386 97 0 4 1 @noBuf)
		(= ret
			(PrintD @str
				#new
				#at x y
				#new
				#button @yesBuf 1
				#new
				#button @noBuf 0
			)
		)
		(self dispose:)
		(return ret)
	)
	
	(method (display)
	)
)

(instance tPig of FaceTalker
	(properties
		noun PIGHEAD
		modNum 386
		talkerNum PIGHEAD
		tpType 2
	)
)

(instance tRog of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1008
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 27
		eyeOffsetY 21
	)
)

(instance tRogette of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1009
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 26
		eyeOffsetY 21
	)
)
