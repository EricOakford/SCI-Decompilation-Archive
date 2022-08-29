;;; Sierra Script 1.0 - (do not remove this comment)
(script# 401)
(include sci.sh)
(use Main)
(use Procs)
(use PFollow)
(use PolyPath)
(use StopWalk)
(use Grooper)
(use RegPath)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	JacksonSq 0
	xMime 1
	xSmallStopGroop 2
	xWoman 3
	xMan 4
	xBoy 5
)

(local
	local0
	local1
	local2
	local3
	mimeTalkCount
	local5
	local6
	[manPath 41] = [32767 420 319 81 236 78 198 122 199 176 32767 440 163 15 181 56 181 94 318 94 32767 430 0 93 82 93 129 65 129 10 32767 410 190 171 190 110 139 66 0 66 -32768]
	[womanPath 43] = [32767 410 0 68 116 68 208 98 208 177 32767 430 236 9 236 62 217 73 0 73 32767 440 319 82 212 92 185 63 185 6 32767 420 151 175 151 117 186 117 217 72 319 70 -32768]
	[boyPath 41] = [32767 430 0 93 82 93 129 65 129 10 32767 410 190 171 190 110 139 66 0 66 32767 420 319 79 236 78 198 122 199 176 32767 440 163 15 181 56 181 94 319 94 -32768]
	[grannyPath 43] = [32767 440 319 72 212 72 175 63 175 6 32767 420 151 175 151 117 186 117 217 72 319 72 32767 410 0 68 116 68 208 98 208 177 32767 430 236 9 236 62 217 73 0 73 -32768]
)
(class JacksonSq of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
	)
	
	(method (init)
		(= mimeTalkCount 0)
		((= gGk1Exits gk1Exits) add:)
		(super init:)
		(xBoy init:)
		(xGranny init:)
		(xWoman init:)
		(xMan init:)
		(= local5 0)
		(if (and (not (Btst 101)) (!= curRoom 420))
			(xMime x: 1000)
		)
	)
	
	(method (doit)
		(if
			(and
				(gGk1Exits size?)
				(== (theIconBar curIcon?) (theIconBar walkIconItem?))
			)
			(gGk1Exits eachElementDo: #doit)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 984)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (== modNum -1) (= modNum curRoomNum))
		(return
			(cond 
				((Message msgGET modNum noun theVerb 0 1) (messager say: noun theVerb 0 0 0 modNum) 1)
				((Message msgGET modNum noun 0 0 1) (messager say: noun 0 0 0 0 modNum) 1)
				(else (messager say: 0 theVerb 0 0 0 0) 1)
			)
		)
	)
	
	(method (newRoom n)
		(= initialized 0)
		(if (xWoman mover?) (xWoman setMotion: 0))
		(if (xMan mover?) (xMan setMotion: 0))
		(if (xBoy mover?) (xBoy setMotion: 0))
		(if (xGranny mover?) (xGranny setMotion: 0))
		(= keep (OneOf n 410 420 430 440))
		(if (gGk1Exits size?)
			(gGk1Exits eachElementDo: #dispose)
			(gGk1Exits dispose:)
		)
		(super newRoom: n &rest)
	)
)

(instance pedBaseSetter of Code
	(properties)
	
	(method (doit param1)
		(param1
			brLeft: (- (param1 x?) 1)
			brRight: (+ (param1 x?) 1)
			brTop: (- (param1 y?) 1)
			brBottom: (param1 y?)
		)
	)
)

(class PedActor of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 6
		modNum 401
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop -1
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		mimeAttached 0
		room 0
		lastRoom 0
		path 0
	)
	
	(method (init)
		(super init:)
		(= baseSetter pedBaseSetter)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((and (self isBlocked:) (not local5)) (= local5 1) (self setScript: sAvoid))
			(
				(and
					(== room curRoomNum)
					(self mimeAttach: xMime)
					(Btst 101)
					(not (xMime script?))
					(not mimeAttached)
				)
				(Bclr 101)
				(= mimeAttached 1)
				(self setScript: sMimeFollowsPed)
			)
		)
		(cond 
			(script)
			((not mover) (self setMotion: path))
		)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 8 6 12 10 11 9 7 13)
			(super doVerb: theVerb)
		)
	)
	
	(method (mimeAttach param1)
		(return
			(if
				(and
					(<= (- x 20) (param1 x?))
					(>= (+ x 20) (param1 x?))
					(<= (- y 15) (param1 y?))
				)
				(>= (+ y 15) (param1 y?))
			else
				0
			)
		)
	)
)

(instance gk1Exits of EventHandler
	(properties)
)

(instance sAvoid of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(client ignoreActors: 1)
				(switch (client loop?)
					(0
						(= local0 2)
						(= local1 8)
						(= local2 16)
						(= local3 -8)
					)
					(1
						(= local0 -2)
						(= local1 -8)
						(= local2 -16)
						(= local3 8)
					)
					(3
						(= local0 8)
						(= local1 0)
						(= local2 -8)
						(= local3 -8)
					)
					(else 
						(= local0 -8)
						(= local1 0)
						(= local2 8)
						(= local3 8)
					)
				)
				(= cycles 1)
			)
			(1
				(client
					setMotion: MoveTo (+ (client x?) local0) (+ (client y?) local1) self
				)
			)
			(2
				(client
					setMotion: MoveTo (+ (client x?) local2) (+ (client y?) local3) self
				)
			)
			(3
				(theGame handsOn:)
				(= local5 0)
				(client ignoreActors: 0)
				(self dispose:)
			)
		)
	)
)

(instance sMimeFollowsPed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScript: 0)
				(xMime setScript: 0)
				(client setMotion: 0)
				(switch (client view?)
					(416
						(= local0 (+ (client x?) 15))
						(= local2 (- (client x?) 45))
						(= local3 (- (client x?) 30))
						(= local6 1)
						(= register 1)
					)
					(417
						(= local0 (- (client x?) 15))
						(= local2 (+ (client x?) 45))
						(= local3 (+ (client x?) 30))
						(= local6 0)
						(= register 0)
					)
					(418
						(= local0 (+ (client x?) 15))
						(= local2 (- (client x?) 45))
						(= local3 (- (client x?) 30))
						(= local6 1)
						(= register 2)
					)
					(else 
						(= local0 (- (client x?) 15))
						(= local2 (+ (client x?) 45))
						(= local3 (+ (client x?) 30))
						(= local6 0)
						(= register 3)
					)
				)
				(= cycles 1)
			)
			(1
				(client ignoreActors: 1)
				(xMime
					view: 422
					ignoreHorizon: 1
					setSpeed: 6
					setCycle: StopWalk 422
					setLoop: -1
					setPri: -1
					setMotion: PolyPath local0 (client y?) self
				)
			)
			(2
				(client setMotion: MoveTo local2 (client y?) self)
				((ScriptID 401 1)
					view: 4201
					setStep: 2 1
					setLoop: local6
					setMotion: MoveTo local3 (client y?)
				)
			)
			(3
				(if modelessDialog (modelessDialog dispose:))
				(= cycles 1)
			)
			(4
				(switch register
					(1
						(messager say: 10 0 12 0 0 401)
					)
					(0
						(messager say: 10 0 10 0 0 401)
					)
					(2
						(messager say: 10 0 11 0 0 401)
					)
					(3
						(messager say: 10 0 9 0 0 401)
					)
				)
				(Face client xMime)
				(xMime setCycle: 0)
				(= ticks 120)
			)
			(5
				((ScriptID 401 1)
					setLoop: (+ (xMime loop?) 2)
					setCel: 0
					setCycle: End self
				)
			)
			(6
				(client ignoreActors: 0)
				(xMime
					view: 422
					ignoreHorizon: 1
					setSpeed: 6
					setStep: 3 2
					setCycle: StopWalk 422
					setLoop: -1
					setPri: -1
				)
				(switch register
					(1
						(= local0 (+ (xMime x?) 16))
					)
					(0
						(= local0 (- (xMime x?) 16))
					)
					(2
						(= local0 (+ (xMime x?) 16))
					)
					(3
						(= local0 (- (xMime x?) 16))
					)
				)
				(switch curRoomNum
					(410 (self changeState: 7))
					(420 (self changeState: 10))
					(430 (self changeState: 12))
					(else  (self changeState: 15))
				)
			)
			(7
				(xMime x: local0 setMotion: PolyPath 5 63 self)
			)
			(8
				(xMime setMotion: MoveTo -20 63 self)
			)
			(9
				(theGame handsOn:)
				(client mimeAttached: 0)
				(self dispose:)
			)
			(10
				(xMime x: local0 setMotion: PolyPath 245 63 self)
			)
			(11
				(theGame handsOn:)
				(xMime setScript: (ScriptID 420 1))
				(client mimeAttached: 0)
				(self dispose:)
			)
			(12
				(xMime x: local0 setMotion: PolyPath 5 70 self)
			)
			(13
				(xMime setMotion: MoveTo -20 70 self)
			)
			(14
				(theGame handsOn:)
				(client mimeAttached: 0)
				(self dispose:)
			)
			(15
				(xMime x: local0 setMotion: PolyPath 158 32 self)
			)
			(16
				(xMime setMotion: MoveTo 158 5 self)
			)
			(17
				(theGame handsOn:)
				(client mimeAttached: 0)
				(self dispose:)
			)
		)
	)
)

(instance sMimeStop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(xMime
					setMotion: 0
					view: 422
					setLoop:
					(switch (ego cel?)
						((OneOf 0 2 4 6) 9)
						(else  10)
					)
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(if
					(or
						(not (ego mover?))
						(and (ego mover?) (& (ego signal?) $0400))
					)
					(-- state)
				)
				(= cycles 1)
			)
			(2
				(xMime
					view: 422
					setScript: 0
					setCycle: StopWalk 422
					setLoop: -1
					setPri: -1
				)
				(= cycles 1)
			)
			(3
				(xMime setMotion: PFollow ego 15)
				(self dispose:)
			)
		)
	)
)

(instance sPunchMime of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 101)
				(xMime
					view: 422
					setCycle: StopWalk 422
					setLoop: -1
					setPri: -1
					setMotion: MoveTo (- (ego x?) 26) (+ (ego y?) 1) self
				)
			)
			(1
				(xMime hide:)
				(ego script: 0 view: 4101 loop: 0 setCel: 0 setCycle: 0)
				(= seconds 4)
			)
			(2
				(ego setSpeed: 12 setCycle: End self)
			)
			(3 (= ticks 120))
			(4
				(ego setLoop: 1 setCel: 0 setCycle: End self)
			)
			(5
				(xMime
					view: 4101
					loop: 2
					posn: (- (ego x?) 26) (+ (ego y?) 1)
					show:
				)
				(ego
					normalize: 1 902
					setLoop: (ScriptID 401 2)
					setStep: 2 1
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance womanPath of RegionPath
	(properties
		theRegion 401
	)
	
	(method (at param1)
		(client room: currentRoom)
		(return [womanPath param1])
	)
)

(instance manPath of RegionPath
	(properties
		theRegion 401
	)
	
	(method (at param1)
		(client room: currentRoom)
		(return [manPath param1])
	)
)

(instance grannyPath of RegionPath
	(properties
		theRegion 401
	)
	
	(method (at param1)
		(client room: currentRoom)
		(return [grannyPath param1])
	)
)

(instance boyPath of RegionPath
	(properties
		theRegion 401
	)
	
	(method (at param1)
		(client room: currentRoom)
		(return [boyPath param1])
	)
)

(instance xMime of Actor
	(properties
		noun 5
		modNum 401
		view 422
		loop 1
		signal $4000
	)
	
	(method (doit)
		(if
			(and
				(Btst 101)
				(!= (xMime moveSpeed?) (ego moveSpeed?))
			)
			(self setSpeed: (ego moveSpeed?))
		)
		(if
			(and
				(Btst 101)
				(not (ego script?))
				(not (ego mover?))
				(not (xMime script?))
				(< (xMime distanceTo: ego) 20)
			)
			(ego setScript: sMimeStop)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				43
				42
				66
				32
				28
				31
				15
				34
				18
				22
				46
				5
				50
				37
				19
				20
				23
				33
				4
				21
				56
				44
				17
				26
				41
				61
				49
				67
				48
				24
				64
				14
				39
				65
				62
				60
				30
				45
				3
				51
				35
				16
				40
				59
				36
				38
			)
			(messager say: noun 0 0 0 0 modNum)
		else
			(switch theVerb
				(7
					(cond 
						((== (xMime view?) 420) (messager say: 5 7 6 0 0 401))
						((not (Btst 101)) (messager say: 5 7 7 0 0 401))
						((Btst 101) (messager say: 5 7 5 0 0 401))
					)
				)
				(12
					(cond 
						((== (xMime view?) 420) (messager say: 5 12 6 0 0 401))
						((not (Btst 101)) (messager say: 5 12 7 0 0 401))
						((Btst 101) (messager say: 5 12 5 0 0 401))
					)
				)
				(11
					(cond 
						((== (xMime view?) 420) (messager say: 5 11 6 0 0 401))
						((not (Btst 101)) (messager say: 5 11 7 0 0 401))
						((Btst 101)
							(if (== mimeTalkCount 10)
								(xMime setScript: sPunchMime)
							else
								(++ mimeTalkCount)
							)
							(messager say: 5 11 5 0 0 401)
						)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance xWoman of PedActor
	(properties
		view 417
		signal $2000
	)
	
	(method (init)
		(self setStep: 2 1 setSpeed: 6 setCycle: Walk)
		(= path womanPath)
		(if (IsObject path) (self setMotion: path))
		(super init: &rest)
	)
)

(instance xGranny of PedActor
	(properties
		view 419
		signal $2000
	)
	
	(method (init)
		(self setStep: 2 1 setSpeed: 6 setCycle: Walk)
		(= path grannyPath)
		(if (IsObject path) (self setMotion: path))
		(super init: &rest)
	)
)

(instance xBoy of PedActor
	(properties
		view 418
		signal $2000
	)
	
	(method (init)
		(self setStep: 2 1 setSpeed: 6 setCycle: Walk)
		(= path boyPath)
		(if (IsObject path) (self setMotion: path))
		(super init: &rest)
	)
)

(instance xMan of PedActor
	(properties
		view 416
		signal $2000
	)
	
	(method (init)
		(self setStep: 2 1 setSpeed: 6 setCycle: Walk)
		(= path manPath)
		(if (IsObject path) (self setMotion: path))
		(super init: &rest)
	)
)

(instance xSmallStopGroop of Grooper
	(properties)
	
	(method (cue &tmp temp0)
		(super cue:)
	)
)
