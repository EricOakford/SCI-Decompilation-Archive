;;; Sierra Script 1.0 - (do not remove this comment)
(script# 568)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use TellerIcon)
(use ForestView)
(use Intrface)
(use Scaler)
(use RandCyc)
(use Polygon)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm568 0
)

(local
	local0
	local1
	local2
)
(instance rm568 of GloryRm
	(properties
		picture 410
		horizon 70
		north 567
		east 572
		south 569
		west 565
		topX 179
		rightY 135
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 0 127 0 127 74 157 85 157 91 98 107 98 113 109 125 109 132 0 132
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 0 319 127 222 127 208 116 151 116 151 106 179 88 242 74 242 0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 211 144 319 144 319 189 254 189 211 168
					yourself:
				)
		)
		(self setRegions: 50)
		(super init: &rest)
		(if
			(and
				Night
				(not global365)
				(not (Btst 35))
				(<= global465 6)
				(> Clock 2100)
			)
			(if debugging (= global465 (GetNumber {event #?_})))
			(RemapColors 2 253 140)
			(= local1 1)
			(Bset 35)
			(Bset 338)
			(anna
				init:
				setScaler: Scaler 100 40 147 61
				setCycle: RandCycle
			)
			(if (and (<= global465 4) (Btst 45)) (= global465 5))
			(switch global465
				(0
					(heroTeller init: ego 568 9 128 6)
					(annaTeller init: anna 568 9 161 6)
					(anna setScript: sMoveToward)
				)
				(1
					(heroTeller init: ego 568 9 128 1)
					(annaTeller init: anna 568 9 161 1)
					((ScriptID 50 1) caller: self)
				)
				(2
					(heroTeller init: ego 568 9 128 2)
					(annaTeller init: anna 568 9 161 2)
					((ScriptID 50 1) caller: self)
				)
				(3
					(Bset 339)
					(= local0 4)
					(heroTeller init: ego 568 9 128 3)
					(annaTeller init: anna 568 9 161 3)
					((ScriptID 50 1) caller: self)
				)
				(4
					(heroTeller init: ego 568 9 128 4)
					(annaTeller init: anna 568 9 161 4)
					((ScriptID 50 1) caller: self)
				)
				(5
					(= local0 6)
					((ScriptID 50 1) caller: self)
				)
				(6
					(= local0 100)
					(heroTeller init: ego 568 9 128 5)
					(annaTeller init: anna 568 9 161 5)
					(nikolaiTeller init: nikolai 568 9 162 5)
					(nikolai
						init:
						setScaler: Scaler 100 40 147 61
						signal: (| (nikolai signal?) $0001)
						setCycle: RandCycle
					)
					((ScriptID 50 1) caller: self)
				)
			)
			(Bset 380)
		)
		(if (and local1 (== global465 5)) (theGame handsOff:))
		(curRoom
			addPoly:
				((Polygon new:)
					init: 130 77 236 78 216 96 120 115 159 128 127 139 73 116
					yourself:
				)
				60
				((Polygon new:)
					init: 319 189 177 189 147 174 319 126
					yourself:
				)
				60
				((Polygon new:)
					init: 0 125 36 135 19 143 59 189 0 189
					yourself:
				)
				130
		)
		(atp0 init: setPri: 116)
		(atp1 init: setPri: 159)
		(if Night
			(atp2 init: setPri: 249)
			(atp3 init: setPri: 249)
			(atp4 init: setPri: 249)
			(atp5 init: setPri: 249)
		)
		(if (and (> global465 10) (not (Btst 425)))
			(theHat init:)
		)
		(if (Btst 380) (theGame save: 1))
	)
	
	(method (dispose)
		(if local1 (Bclr 35) (if local0 (= global465 local0)))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(83
				(if (cast contains: anna)
					(curRoom setScript: (ScriptID 12) anna 83)
				else
					(curRoom setScript: (ScriptID 12) 0 83)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(switch global465
			(1 (messager say: 7 6 14))
			(2 (messager say: 7 6 15))
			(3 (messager say: 7 6 16))
			(4 (messager say: 7 6 16))
			(5
				(curRoom setScript: sReunited)
			)
			(6 (messager say: 8 6 20))
		)
	)
)

(instance anna of TargActor
	(properties
		noun 7
		x 134
		y 109
		view 569
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(= cycleSpeed (* 2 defaultCycles))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (self getHurt:))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(anna getHurt:)
	)
	
	(method (getHurt)
		(self setScript: sVanish)
	)
)

(instance nikolai of TargActor
	(properties
		noun 8
		x 210
		y 109
		view 568
		loop 1
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= cycleSpeed (* 2 defaultCycles))
	)
	
	(method (getHurt)
		(anna getHurt:)
	)
)

(instance theHat of Actor
	(properties
		noun 11
		x 190
		y 112
		view 905
		loop 6
		cel 7
		signal $4801
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(theHat dispose:)
				(ego get: 26 1)
				(ego solvePuzzle: 425 2)
				(messager say: noun theVerb 0)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance doSayMessage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager
					say:
						(heroTeller sayNoun?)
						(heroTeller verb?)
						(heroTeller iconValue?)
						0
						self
				)
			)
			(1 (client setScript: sVanish))
		)
	)
)

(instance heroTeller of Teller
	(properties
		loopMenu 0
	)
	
	(method (respond)
		(super respond: &rest)
		(if (== iconValue -999) ((User curEvent?) claimed: 1))
		(return 1)
	)
	
	(method (sayMessage)
		(Bclr 51)
		(switch global465
			(0
				(= local0 1)
				(if (== iconValue 13)
					(self clean:)
					(anna setScript: doSayMessage)
				else
					(super sayMessage: &rest)
				)
			)
			(1
				(= local0 2)
				(if (== iconValue 2)
					(self clean:)
					(anna setScript: doSayMessage)
				else
					(super sayMessage: &rest)
				)
			)
			(2
				(if (== iconValue 4)
					(ego solvePuzzle: 423 6)
					(= local0 3)
					(self clean:)
					(anna setScript: doSayMessage)
				else
					(super sayMessage: &rest)
				)
			)
			(3
				(if (== iconValue 43)
					(= local0 4)
					(self clean:)
					(anna setScript: doSayMessage)
				else
					(super sayMessage: &rest)
				)
			)
			(4
				(if (== iconValue 44)
					(self clean:)
					(anna setScript: doSayMessage)
				else
					(super sayMessage: &rest)
				)
			)
			(6
				(if (== iconValue 42)
					(self clean:)
					(anna setScript: doSayMessage)
				else
					(super sayMessage: &rest)
				)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
)

(instance annaTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 75))
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 21 4 36 37)
			(anna getHurt:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (sayMessage)
		(switch global465
			(0
				(= local0 1)
				(if (== iconValue 13)
					(self clean:)
					(anna setScript: sVanish 0 13)
				else
					(super sayMessage: &rest)
				)
			)
			(1
				(= local0 2)
				(if (== iconValue 2)
					(self clean:)
					(anna setScript: sVanish 0 2)
				else
					(super sayMessage: &rest)
				)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
)

(instance nikolaiTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 106 0))
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 21 4 36 37)
			(anna getHurt:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (sayMessage)
		(Bclr 51)
		(if (== iconValue 37)
			(ego solvePuzzle: 425 2)
			(ego get: 26 1)
		)
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super showCases: 37 (not (Btst 425)))
	)
)

(instance atp0 of ForestView
	(properties
		x 71
		y 6
		view 414
		cel 5
	)
)

(instance atp1 of ForestView
	(properties
		x 210
		y 19
		z -100
		view 414
		cel 6
	)
)

(instance atp2 of ForestView
	(properties
		x 28
		z -189
		view 417
	)
)

(instance atp3 of ForestView
	(properties
		x 18
		y 77
		view 417
		cel 1
	)
)

(instance atp4 of ForestView
	(properties
		x 258
		z -189
		view 418
		loop 1
		cel 1
	)
)

(instance atp5 of ForestView
	(properties
		x 318
		y 20
		view 418
	)
)

(instance sVanish of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(if register
					(messager say: 9 161 register 0 self)
				else
					(= cycles 1)
				)
			)
			(2
				(anna hide:)
				(heroTeller dispose:)
				(annaTeller dispose:)
				(if (cast contains: nikolai)
					(nikolaiTeller dispose:)
					(cast delete: nikolai)
					(nikolai dispose:)
				)
				(messager say: 7 4 0 0 self)
			)
			(3
				(anna hide:)
				(if (and (== global465 6) (not (Btst 425)))
					(theHat
						init:
						approachVerbs: 4
						x: 190
						y: 74
						approachX: 190
						approachY: 117
						setMotion: MoveTo 190 113 self
					)
				else
					(self cue:)
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sMoveToward of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(anna
					setStep: 1 1
					moveSpeed: 12
					setMotion: MoveTo (ego x?) (ego y?)
				)
				(= seconds 3)
			)
			(2 (self changeState: 1))
		)
	)
)

(instance sReunited of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(nikolai
					init:
					setScaler: Scaler 100 40 147 61
					signal: (| (nikolai signal?) $0001)
					setCycle: RandCycle
				)
				(= ticks 60)
			)
			(1
				(messager say: 8 6 18 0 self)
			)
			(2
				(nikolai
					signal: (| (nikolai signal?) $0001)
					setPri: (+ (anna priority?) 1)
					setMotion: MoveTo (+ (anna x?) 30) (- (anna y?) 7) self
				)
			)
			(3
				(nikolai setLoop: 2 setCel: 0 setCycle: End self)
				(anna setLoop: 2 setCel: 0 setCycle: End self)
			)
			(4 0)
			(5
				(nikolai hide:)
				(ego solvePuzzle: 424 6)
				(anna
					view: 568
					setLoop: 3
					setCel: 0
					x: (+ (anna x?) 7)
					setCycle: End self
				)
			)
			(6
				(messager say: 8 6 19 0 self)
			)
			(7
				(anna setScript: sVanish)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
