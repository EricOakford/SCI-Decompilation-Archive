;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include sci.sh)
(use Main)
(use TPSound)
(use DialogPlane)
(use L7Room)
(use CueMe)
(use NewUser)
(use GenDialog)
(use Talker)
(use Osc)
(use ForCount)
(use Motion)
(use Actor)
(use System)

(public
	roEndDemoRoom 0
)

(local
	local0
)
(procedure (localproc_131d param1)
	(if (ResCheck 139 param1)
		(Palette 1 param1)
	else
		(PrintDebug {Palette %d is inaccessible.} param1)
	)
	(return 1)
)

(instance roEndDemoRoom of L7Room
	(properties)
	
	(method (init)
		(super init: &rest)
		(if ((ScriptID 64017 0) test: 5)
			(theGame handsOff:)
			(= gToLarry toLarry)
			(= gToThygh toDewmi)
			(gOSound1 stop:)
			(gOEventHandler registerGlobalHandler: oAnyEventHandler)
			(if
			(or (== prevRoomNum 9000) ((ScriptID 64017 0) test: 6))
				(if global329
					(self setScript: soBeginLarryWon)
				else
					(self setScript: soBeginLarryLost)
				)
			else
				(self setScript: soCommonDemoEnd)
			)
		else
			(curRoom newRoom: 300)
		)
	)
	
	(method (dispose)
		(= gToLarry 0)
		(= gToThygh 0)
		(super dispose: &rest)
	)
	
	(method (newRoom)
		(gOEventHandler unregisterGlobalHandler: oAnyEventHandler)
		(super newRoom: &rest)
	)
)

(instance soBeginLarryWon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(gOMusic1 stop: setMusic: 13000)
				(localproc_131d 115)
				(roEndDemoRoom drawPic: 11600)
				(poDewmi view: 11500 loop: 0 cel: 0 x: 170 y: 540 init:)
				(UpdateScreenItem poDewmi)
				(voDewmi init:)
				(poDewmiEyes init:)
				(= gToThygh toDewmi)
				(messager say: 0 0 0 1 self 3)
			)
			(2
				(voDewmi hide:)
				(poDewmi hide:)
				(poDewmiEyes hide:)
				(localproc_131d 118)
				(roEndDemoRoom drawPic: 11850)
				(= gToLarry 0)
				(voLarryBod view: 11800 x: 0 y: 480 init:)
				(poLarryHead
					view: 11800
					x: 0
					y: 480
					loop: 1
					cycleSpeed: 4
					init:
					setCycle: Osc
				)
				(messager say: 0 0 0 2 self 3)
			)
			(3
				(voDewmi show:)
				(poDewmi show:)
				(poDewmiEyes show:)
				(voLarryBod dispose:)
				(poLarryHead dispose:)
				(roEndDemoRoom drawPic: 11600)
				(messager say: 0 0 0 3 self 3)
			)
			(4
				(voDewmi hide:)
				(poDewmi hide:)
				(poDewmiEyes hide:)
				(= gToLarry toLarry)
				(roEndDemoRoom setScript: soCommonDemoEnd)
				(self dispose:)
			)
		)
	)
)

(instance soBeginLarryLost of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(localproc_131d 118)
				(roEndDemoRoom drawPic: 11800)
				(= ticks 30)
			)
			(2
				(messager say: 0 0 1 1 self 3)
			)
			(3
				(gOMusic1 stop: setMusic: 13000)
				(roEndDemoRoom setScript: soCommonDemoEnd)
				(self dispose:)
			)
		)
	)
)

(instance soCommonDemoEnd of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not ((ScriptID 64017 0) test: 8))
					(gOMusic1 stop: setMusic: 13000)
				)
				(= cycles 1)
			)
			(1
				(messager say: 0 0 0 4 self 3)
				(localproc_131d 119)
				(roEndDemoRoom drawPic: 11900)
				(aoYankLarry
					init:
					setSpeed: 8
					setCycle: ForwardCounter 3 self
				)
			)
			(2 0)
			(3
				(= local0 0)
				(messager say: 0 0 0 5 coEndTalk 3)
				(aoYankLarry setSpeed: 6 setMotion: MoveTo 230 270 self)
			)
			(4
				(aoYankLarry
					view: 11900
					loop: 0
					cel: 0
					setCycle: ForwardCounter 1 self
				)
			)
			(5
				(if (not local0) (= state (- state 1)))
				(= cycles 1)
			)
			(6
				(voPortPix init:)
				(= local0 0)
				(gOSound1 playSound: 304)
				(messager say: 0 0 0 6 coEndTalk 3)
				(aoYankLarry
					cel: 0
					setSpeed: 4
					setMotion: MoveTo 230 180 self
				)
			)
			(7 (= ticks 45))
			(8
				(aoYankLarry
					view: 11900
					loop: 0
					cel: 0
					setCycle: ForwardCounter 1 self
				)
			)
			(9
				(aoYankLarry
					cel: 0
					setSpeed: 2
					setMotion: MoveTo 230 90 self
				)
			)
			(10
				(gOSound1 preload: 303 301 302)
				(aoYankLarry setCycle: ForwardCounter 1 self)
			)
			(11
				(aoYankLarry
					cel: 0
					setSpeed: 1
					setStep: 20 20
					setMotion: MoveTo 230 337
				)
				(= cycles 1)
			)
			(12
				(if (not local0) (= state (- state 1)))
				(= cycles 1)
			)
			(13
				(gOSound1 playSound: 303)
				(oLocalSound playSound: 300)
				(aoYankLarry
					setMotion: 0
					cel: 1
					setLoop: 1 1
					setSpeed: 5
					posn: 230 422
					setCycle: End self
				)
				(= ticks 40)
			)
			(14
				(gOSound1 playSound: 301 self)
			)
			(15 0)
			(16
				(gOSound1 playSound: 302)
				(Load rsPIC 12000)
				(= cycles 1)
			)
			(17
				(messager say: 0 0 0 7 self 3)
				(voPortPix dispose:)
				(aoYankLarry dispose:)
				(localproc_131d 120)
				(roEndDemoRoom drawPic: 12000)
				(voDummyBust
					view: 12000
					loop: 0
					cel: 0
					x: 340
					y: 400
					init:
				)
				(voDummyMouth
					view: 12000
					loop: 1
					cel: 0
					x: 340
					y: 400
					init:
				)
				(voDummyMouth hide:)
				(voDummyBust hide:)
				(poLarryScratch
					view: 12000
					loop: 0
					cel: 0
					x: 340
					y: 400
					init:
					setCycle: End
				)
				(poYankLarry
					view: 12000
					loop: 1
					cel: 0
					x: 340
					y: 400
					init: 7
				)
				(= ticks 180)
			)
			(18
				(poYankLarry hide:)
				(poLarryScratch
					view: 12000
					loop: 2
					cel: 0
					x: 340
					y: 400
					setCycle: End self
				)
			)
			(19)
			(20
				(Load rsVIEW 12100)
				(= ticks 10)
			)
			(21
				(poLarryScratch dispose:)
				(localproc_131d 121)
				(roEndDemoRoom drawPic: 12100)
				(poLarry view: 12100 x: 240 y: 383 init:)
				(UpdateScreenItem poLarry)
				(voLarry init:)
				(messager say: 0 0 0 8 self 3)
			)
			(22
				(messager say: 0 0 0 9 self 3)
				(= ticks 80)
			)
			(23
				(poLarry hide:)
				(voLarry hide:)
				(poStillLarry
					view: 12100
					loop: 2
					cel: 0
					x: 240
					y: 383
					init:
				)
			)
			(24
				(localproc_131d 122)
				(roEndDemoRoom drawPic: 12200)
				(poStillLarry hide:)
				(messager say: 0 0 0 10 self 3)
			)
			(25
				(poDewmi
					view: 12200
					loop: 0
					x: 640
					y: 480
					priority: 500
					init:
					hide:
				)
				(= gToThygh toLastDewmi)
				(= local0 0)
				(messager say: 0 0 0 11 coEndTalk 3)
				(poDewmiFinale
					view: 12200
					loop: 2
					cel: 0
					x: 640
					y: 480
					cycleSpeed: 5
					init:
					setCycle: End self
				)
			)
			(26
				(if (not (talkers isEmpty:))
					((toLastDewmi mouth?) show:)
				)
				(self cue:)
			)
			(27
				(if (not local0) (= state (- state 1)))
				(= cycles 1)
			)
			(28
				(curRoom newRoom: 300)
				(self dispose:)
			)
		)
	)
)

(instance coEndTalk of CueMe
	(properties)
	
	(method (cue)
		(= local0 1)
	)
)

(instance voDummyBust of View
	(properties)
)

(instance voDummyMouth of View
	(properties)
)

(instance poDewmiFinale of Prop
	(properties)
)

(instance poStillLarry of Prop
	(properties)
)

(instance voLarryBod of View
	(properties
		priority 1
		fixPriority 1
	)
)

(instance poLarryHead of Prop
	(properties
		priority 100
		fixPriority 1
	)
)

(instance poYankLarry of Prop
	(properties
		x 230
		y 300
		view 11900
		cycleSpeed 6
	)
	
	(method (init param1)
		(super init: &rest)
		(self setCycle: ForwardCounter param1)
	)
)

(instance poAnimation_A of Prop
	(properties
		x 230
		y 300
		view 10100
		cycleSpeed 6
	)
	
	(method (init param1)
		(super init: &rest)
		(self setCycle: ForwardCounter param1)
	)
)

(instance poLarryScratch of Prop
	(properties
		x 230
		y 300
		view 10100
		cycleSpeed 6
	)
)

(instance aoYankLarry of Actor
	(properties
		x 230
		y 330
		view 11900
		yStep 5
		xStep 0
		moveSpeed 3
	)
)

(instance voPortPix of View
	(properties
		x 62
		y 307
		priority 5
		fixPriority 1
		view 11950
	)
)

(instance poLarry of Prop
	(properties
		x 340
		y 322
		view 10400
	)
)

(instance voLarry of View
	(properties)
	
	(method (init)
		(= view (poLarry view?))
		(= loop (+ (poLarry loop?) 1))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(self setPri: (+ (poLarry priority?) 1))
		(super init: &rest)
	)
	
	(method (show)
		(= view (poLarry view?))
		(= loop (+ (poLarry loop?) 1))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(self setPri: (+ (poLarry priority?) 1))
		(super show: &rest)
	)
)

(instance toLarry of Talker
	(properties)
	
	(method (init)
		(= view (poLarry view?))
		(= loop (+ (poLarry loop?) 1))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(= priority (+ (poLarry priority?) 1))
		(voLarry hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(voLarry show:)
		(super dispose: &rest)
	)
)

(instance poLarryEyes of Prop
	(properties)
	
	(method (init)
		(= view (poLarry view?))
		(= loop (+ (poLarry loop?) 2))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(self setPri: (+ (poLarry priority?) 15))
		(super init: &rest)
		(self cycleSpeed: 6)
		(self setCycle: Blink 10)
	)
	
	(method (show)
		(= view (poLarry view?))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(super show: &rest)
		(self setCycle: Blink 10)
	)
)

(instance poDewmi of Prop
	(properties
		x 296
		y 264
		priority 5
		fixPriority 1
		view 10600
	)
)

(instance voDewmi of View
	(properties)
	
	(method (init)
		(= view (poDewmi view?))
		(= loop (+ (poDewmi loop?) 1))
		(= x (poDewmi x?))
		(= y (poDewmi y?))
		(self setPri: (+ (poDewmi priority?) 1))
		(super init: &rest)
	)
	
	(method (show)
		(= view (poDewmi view?))
		(= loop (+ (poDewmi loop?) 1))
		(= x (poDewmi x?))
		(= y (poDewmi y?))
		(self setPri: (+ (poDewmi priority?) 1))
		(super show: &rest)
	)
)

(instance toDewmi of Talker
	(properties)
	
	(method (init)
		(= view (poDewmi view?))
		(= loop (+ (poDewmi loop?) 1))
		(= x (poDewmi x?))
		(= y (poDewmi y?))
		(= priority (+ (poDewmi priority?) 1))
		(voDewmi hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(voDewmi show:)
		(super dispose: &rest)
	)
)

(instance poDewmiEyes of Prop
	(properties)
	
	(method (init)
		(= view (poDewmi view?))
		(= loop (+ (poDewmi loop?) 2))
		(= x (poDewmi x?))
		(= y (poDewmi y?))
		(self setPri: (+ (poDewmi priority?) 15))
		(super init: &rest)
		(self cycleSpeed: 6)
		(self setCycle: Blink 10)
	)
	
	(method (show)
		(= view (poDewmi view?))
		(= x (poDewmi x?))
		(= y (poDewmi y?))
		(super show: &rest)
		(self setCycle: Blink 10)
	)
)

(instance toLastDewmi of Talker
	(properties)
	
	(method (init)
		(= view (poDewmi view?))
		(= loop (+ (poDewmi loop?) 1))
		(= x (poDewmi x?))
		(= y (poDewmi y?))
		(= priority (+ (poDewmi priority?) 1))
		(super init: &rest)
		(mouth hide:)
	)
)

(instance oLocalSound of TPSound
	(properties)
)

(instance oAnyEventHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp temp0)
		(if
			(or
				(== (event type?) evMOUSEBUTTON)
				(== (event type?) evKEYBOARD)
			)
			(if
				(and
					(<= emRIGHT_SHIFT (event modifiers?))
					(<= (event modifiers?) emALT)
				)
				(return 0)
			)
			(gOEventHandler unregisterGlobalHandler: oAnyEventHandler)
			(switch
				(= temp0
					(proc64033_7
						4
						0
						0
						(MakeMessageText 0 0 0 5 300)
						(MakeMessageText 0 0 0 2 300)
						(MakeMessageText 0 0 0 3 300)
						(MakeMessageText 0 0 0 4 300)
					)
				)
				(0 (= temp0 0))
				(1
					((ScriptID 64017 0) set: 5)
					((ScriptID 64017 0) set: 6)
					((ScriptID 64017 0) set: 7)
					((ScriptID 64017 0) clear: 8)
					(curRoom newRoom: 9000)
					(return 1)
				)
				(2
					((ScriptID 64017 0) set: 5)
					((ScriptID 64017 0) clear: 6)
					((ScriptID 64017 0) set: 7)
					((ScriptID 64017 0) set: 8)
				)
				(3
					((ScriptID 64017 0) set: 5)
					((ScriptID 64017 0) clear: 6)
					((ScriptID 64017 0) clear: 7)
					((ScriptID 64017 0) clear: 8)
					(= quit 1)
					(return 1)
				)
			)
			(gOEventHandler registerGlobalHandler: oAnyEventHandler)
		)
		(return 1)
	)
)
