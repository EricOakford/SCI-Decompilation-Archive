;;; Sierra Script 1.0 - (do not remove this comment)
(script# 579)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use ForestView)
(use Intrface)
(use Array)
(use PolyPath)
(use Polygon)
(use ForCount)
(use Sound)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm579 0
)

(local
	leshyEvent
	theBush6
	[local2 2]
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	local11
	local12
	bush5X
	bush5Y
	local15
	local16
	local17
	local18
	local19
)
(procedure (localproc_00fa param1 param2 &tmp temp0)
	(return
		(cond 
			(
				(<
					(= temp0 (GetAngle param1 param2 (ego x?) (ego y?)))
					90
				)
				(return 2)
			)
			((< temp0 180) (return 0))
			((< temp0 270) (return 1))
			(else (return 3))
		)
	)
)

(instance rm579 of GloryRm
	(properties
		noun 1
		picture 430
		horizon 79
		north 578
		east 585
		west 571
		topX 188
	)
	
	(method (init)
		(if
			(and
				(not Night)
				(not (Btst 255))
				(not (Btst 35))
				(not global365)
			)
			(= local16 1)
			(= local4 (IntArray with: 0 0 0 0))
			(= local5 (IntArray with: 0 0 0 0))
			(= local6 (IntArray with: -15 105 76 105 76 123 -15 123))
			(= local7
				(IntArray with: 102 117 195 117 195 131 102 131)
			)
			(= local8 (IntArray with: 195 96 271 96 271 109 195 109))
			(= local9
				(IntArray with: 241 143 335 143 335 162 241 162)
			)
			(= local10
				(IntArray with: 126 151 230 151 230 169 126 169)
			)
			(= local11
				(IntArray with: -10 153 88 153 88 171 -10 171)
			)
			(Bset 35)
			(cond 
				(debugging (= leshyEvent (GetNumber {Leshy Event? (1 - 8)})))
				((not (Btst 256)) (= leshyEvent 1))
				((not (Btst 257)) (= leshyEvent 2))
				((not (Btst 199)) (= leshyEvent 3))
				((not (Btst 186)) (= leshyEvent 4))
				((not (Btst 258)) (= leshyEvent 5))
				((not (Btst 259)) (= leshyEvent 6))
				((not (Btst 260)) (= leshyEvent 7))
				(else (= leshyEvent 8))
			)
			(switch prevRoomNum
				(578 (= theBush6 bush6))
				(585 (= theBush6 bush1))
				(else  (= theBush6 bush4))
			)
			(heroTeller init: ego 579 7 128 2)
			((ScriptID 50 1) caller: self)
			(bushPoly1 points: local6 size: 4)
			(bushPoly3 points: local8 size: 4)
			(bushPoly4 points: local9 size: 4)
			(bushPoly6 points: local11 size: 4)
			(if (not (OneOf leshyEvent 4 7 8))
				(bushPoly2 points: local7 size: 4)
				(bushPoly5 points: local10 size: 4)
				(curRoom
					addObstacle: bushPoly1 bushPoly2 bushPoly3 bushPoly4 bushPoly5 bushPoly6
				)
			else
				(curRoom
					addObstacle: bushPoly1 bushPoly3 bushPoly4 bushPoly6
				)
			)
			(theMusic number: 579 setLoop: -1 play:)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 174 319 174 319 189 0 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 0 169 0 169 96 151 113 80 113 80 103 40 93 0 93
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 0 319 88 246 88 206 80 206 0
					yourself:
				)
		)
		(self setRegions: 50)
		(if (Btst 35)
			(bush1 init: setScaler: ego approachVerbs: 4)
			(if (not (OneOf leshyEvent 4 7 8))
				(bush2 init: setScaler: ego approachVerbs: 4)
			)
			(bush3 init: setScaler: ego approachVerbs: 4)
			(bush4 init: setScaler: ego approachVerbs: 4)
			(if (== leshyEvent 8) (bush5 x: 164 y: 147))
			(bush5 init: setScaler: ego approachVerbs: 4)
			(bush6 init: setScaler: ego approachVerbs: 4)
		)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 166 50 228 88 242 109 135 86
					yourself:
				)
				130
				((Polygon new:)
					init: 301 123 160 189 75 189 53 148 77 115 126 134 165 126 129 109 160 95
					yourself:
				)
				60
		)
		(atp1 init:)
		(if Night
			(atp2 view: 436 loop: 0 cel: 1 x: 79 y: 189)
			(atp3 init: setPri: 189)
		)
		(atp2 init: setPri: 189)
		(if (Btst 380) (theGame save: 1))
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				local16
				(== (curRoom script?) (ScriptID 32))
				(== ((ScriptID 32) state?) 3)
			)
			(if (cast contains: leshy) (leshy dispose:))
			(if (cast contains: bush1) (bush1 dispose:))
			(if (cast contains: bush2) (bush2 dispose:))
			(if (cast contains: bush3) (bush3 dispose:))
			(if (cast contains: bush4) (bush4 dispose:))
			(if (cast contains: bush5) (bush5 dispose:))
			(if (cast contains: bush6) (bush6 dispose:))
			(if ((curRoom obstacles?) contains: bushPoly1)
				((curRoom obstacles?) delete: bushPoly1)
			)
			(if ((curRoom obstacles?) contains: bushPoly2)
				((curRoom obstacles?) delete: bushPoly2)
			)
			(if ((curRoom obstacles?) contains: bushPoly3)
				((curRoom obstacles?) delete: bushPoly3)
			)
			(if ((curRoom obstacles?) contains: bushPoly4)
				((curRoom obstacles?) delete: bushPoly4)
			)
			(if ((curRoom obstacles?) contains: bushPoly5)
				((curRoom obstacles?) delete: bushPoly5)
			)
			(if ((curRoom obstacles?) contains: bushPoly6)
				((curRoom obstacles?) delete: bushPoly6)
			)
			(if (ego actions?) ((ego actions?) dispose:))
			(if (== (theMusic number?) 579)
				(theMusic number: 558 setLoop: -1 play:)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(81 (messager say: 0 81 0))
			((Message msgSIZE 579 0 theVerb 0 1)
				(messager say: 0 theVerb 0 0 0 579)
			)
			(else 
				((ScriptID 50) doVerb: theVerb)
			)
		)
	)
	
	(method (cue)
		(cond 
			(local17 (theMusic number: 558 setLoop: -1 play:))
			((OneOf leshyEvent 1 2 3 5 6) (bush1 setScript: sBushWiggle))
			((OneOf leshyEvent 4 7) (bush5 cycleSpeed: 0 setScript: sRunFarBush))
			((== leshyEvent 8) (bush5 setScript: sLastEvent))
		)
	)
	
	(method (newRoom n)
		(if (== (bush5 script?) sRunFarBush)
			(bush5 setScript: 0)
		)
		(if local16
			(local4 dispose:)
			(local5 dispose:)
			(local6 dispose:)
			(local7 dispose:)
			(local8 dispose:)
			(local9 dispose:)
			(local10 dispose:)
			(local11 dispose:)
			(if (> (++ global455) 2) (= global455 0))
			(theMusic fade:)
			(Bclr 35)
		)
		(super newRoom: n)
	)
)

(instance bushPoly1 of Polygon
	(properties
		type $0002
	)
)

(instance bushPoly2 of Polygon
	(properties
		type $0002
	)
)

(instance bushPoly3 of Polygon
	(properties
		type $0002
	)
)

(instance bushPoly4 of Polygon
	(properties
		type $0002
	)
)

(instance bushPoly5 of Polygon
	(properties
		type $0002
	)
)

(instance bushPoly6 of Polygon
	(properties
		type $0002
	)
)

(instance atp1 of ForestView
	(properties
		x 81
		y 6
		view 434
		cel 1
	)
)

(instance atp2 of ForestView
	(properties
		x 316
		y 154
		view 432
		loop 1
	)
)

(instance atp3 of ForestView
	(properties
		x 202
		y 189
		view 436
		cel 2
	)
)

(instance heroTeller of Teller
	(properties)
	
	(method (respond)
		(super respond: &rest)
		(if
			(and
				(or (not iconValue) (== iconValue -999))
				(== (sEvent1_3_5Room state?) 5)
			)
			(self clean:)
			(sEvent1_3_5Room register: 1 cue:)
		)
		(if (== iconValue -999) ((User curEvent?) claimed: 1))
		(return 1)
	)
)

(instance heroTeller1 of Teller
	(properties)
	
	(method (respond)
		(super respond: &rest)
		(if
			(and
				(or (not iconValue) (== iconValue -999))
				(== (sEvent1_3_5Room state?) 5)
			)
			(self clean:)
			(sEvent1_3_5Room register: 1 cue:)
		)
		(return 1)
	)
	
	(method (sayMessage)
		(if (== iconValue 10)
			(sEvent1_3_5Room register: 0)
			(ego solvePuzzle: 414 2)
			(Bset 256)
		else
			(sEvent1_3_5Room register: 1)
		)
		(messager
			say: sayNoun verb iconValue 0 sEvent1_3_5Room modNum
		)
	)
	
	(method (showCases)
		(super
			showCases:
				10
				(if (Btst 176) else (Btst 135))
				11
				(if (Btst 177) else (Btst 135))
		)
	)
)

(instance heroTeller2 of Teller
	(properties)
	
	(method (sayMessage)
		(if (== iconValue 20)
			(Bset 257)
			(ego solvePuzzle: 416 2)
			(if (== (bush1 script?) sBushWiggle)
				(bush1 setScript: 0)
			)
			(self clean:)
			(curRoom setScript: sEvent2_6Room2)
		else
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super showCases: 21 (Btst 166) 20 (Btst 335))
	)
)

(instance heroTeller3 of Teller
	(properties)
	
	(method (respond)
		(super respond: &rest)
		(if
			(and
				(or (not iconValue) (== iconValue -999))
				(== (sEvent1_3_5Room state?) 5)
			)
			(self clean:)
			(sEvent1_3_5Room cue:)
		)
		(return 1)
	)
	
	(method (sayMessage)
		(if (== iconValue 22)
			(sEvent1_3_5Room register: 2)
		else
			(sEvent1_3_5Room register: 0)
		)
		(messager
			say: sayNoun verb iconValue 0 sEvent1_3_5Room modNum
		)
	)
	
	(method (showCases)
		(super showCases: 22 (Btst 199) 41 (not (Btst 176)))
	)
)

(instance heroTeller4 of Teller
	(properties)
	
	(method (sayMessage)
		(Bclr 51)
		(if (== iconValue 37)
			(ego solvePuzzle: 418 2)
			(self clean:)
			(curRoom setScript: s4_7DoRiddle)
		else
			(Bclr 51)
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super showCases: 37 (if (Btst 177) else (Btst 135)))
	)
)

(instance heroTeller5 of Teller
	(properties)
	
	(method (respond)
		(super respond: &rest)
		(if
			(and
				(or (not iconValue) (== iconValue -999))
				(== (sEvent1_3_5Room state?) 5)
			)
			(self clean:)
			(sEvent1_3_5Room cue:)
		)
		(return 1)
	)
	
	(method (sayMessage)
		(if (== iconValue 50)
			(ego solvePuzzle: 419 2)
			(sEvent1_3_5Room register: 3)
		else
			(sEvent1_3_5Room register: 4)
		)
		(messager
			say: sayNoun verb iconValue 0 sEvent1_3_5Room modNum
		)
	)
	
	(method (showCases)
		(super showCases: 50 (Btst 150))
	)
)

(instance heroTeller6 of Teller
	(properties)
	
	(method (sayMessage)
		(if (== iconValue 51)
			(Bset 259)
			(ego solvePuzzle: 420 2)
			(self clean:)
			(curRoom setScript: sEvent2_6Room2)
		else
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super showCases: 51 (Btst 166))
	)
)

(instance heroTeller7 of Teller
	(properties)
	
	(method (sayMessage)
		(cond 
			((== iconValue 52)
				(ego solvePuzzle: 421 2)
				(self clean:)
				(curRoom setScript: s4_7DoRiddle)
			)
			((== iconValue 53)
				(ego solvePuzzle: 421 2)
				(self clean:)
				(curRoom setScript: s4_7DoRiddle)
			)
			(else (super sayMessage: &rest))
		)
	)
	
	(method (showCases)
		(super
			showCases:
				53
				(!= heroType 1)
				52
				(if (== heroType 1) [egoStats 35] else 0)
		)
	)
)

(instance leshy of Prop
	(properties
		view 425
		signal $4000
	)
	
	(method (init)
		(self setScale:)
		(= scaleX (Random 50 200))
		(= scaleY (Random 50 200))
		(super init: &rest)
	)
)

(instance bush1 of Actor
	(properties
		noun 13
		approachX 33
		approachY 125
		x 33
		y 121
		view 579
		signal $4000
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4)
				(cond 
					((OneOf leshyEvent 1 3 5)
						(if (== self theBush6)
							(curRoom setScript: sEvent1_3_5Room)
						else
							(super doVerb: theVerb)
						)
					)
					((OneOf leshyEvent 2 6)
						(if (or (== self theBush6) local15)
							(= local15 0)
							(if (== (leshy script?) sEvent2_6Room)
								(sEvent2_6Room cue:)
							else
								(if (== (bush1 script?) sBushWiggle)
									(bush1 setScript: 0)
								)
								(leshy
									signal: (| (leshy signal?) $0001)
									init:
									x: 1000
									setScript: sEvent2_6Room
								)
							)
						else
							(super doVerb: theVerb)
						)
					)
					((OneOf leshyEvent 4 7)
						(if (not (s4Pop client?))
							(if (== (sRunFarBush register?) 1)
								(self setScript: s4Pop)
								(sRunFarBush changeState: 0)
							else
								(super doVerb: theVerb)
							)
						)
					)
					(else (super doVerb: theVerb))
				)
			)
			((Message msgSIZE 579 noun theVerb 0 1) (super doVerb: theVerb))
			(else ((ScriptID 50) doVerb: theVerb))
		)
	)
)

(instance bush2 of Prop
	(properties
		noun 13
		x 146
		y 129
		view 579
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== theVerb 4) (OneOf leshyEvent 1 3 5))
				(if (== self theBush6)
					(curRoom setScript: sEvent1_3_5Room)
				else
					(super doVerb: theVerb)
				)
			)
			((and (== theVerb 4) (OneOf leshyEvent 2 6))
				(if (== self theBush6)
					(= local15 1)
					(bush1 doVerb: theVerb)
				else
					(super doVerb: theVerb)
				)
			)
			(else (bush1 doVerb: theVerb))
		)
	)
)

(instance bush3 of Actor
	(properties
		noun 13
		x 235
		y 106
		view 579
		signal $4000
	)
	
	(method (doVerb theVerb)
		(cond 
			(
				(and
					(or (== leshyEvent 4) (== leshyEvent 7))
					(== theVerb 4)
					(not (s4Pop client?))
				)
				(if (== (sRunFarBush register?) 3)
					(self setScript: s4Pop)
					(sRunFarBush changeState: 0)
				else
					(super doVerb: theVerb)
				)
			)
			((and (== theVerb 4) (OneOf leshyEvent 1 3 5))
				(if (== self theBush6)
					(curRoom setScript: sEvent1_3_5Room)
				else
					(super doVerb: theVerb)
				)
			)
			((and (== theVerb 4) (OneOf leshyEvent 2 6))
				(if (== self theBush6)
					(= local15 1)
					(bush1 doVerb: theVerb)
				else
					(super doVerb: theVerb)
				)
			)
			(else (bush1 doVerb: theVerb))
		)
	)
)

(instance bush4 of Actor
	(properties
		noun 13
		approachX 291
		approachY 150
		x 291
		y 159
		view 579
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(cond 
			(
				(and
					(or (== leshyEvent 4) (== leshyEvent 7))
					(== theVerb 4)
					(not (s4Pop client?))
				)
				(if (== (sRunFarBush register?) 4)
					(self setScript: s4Pop)
					(sRunFarBush changeState: 0)
				else
					(super doVerb: theVerb)
				)
			)
			((and (== theVerb 4) (OneOf leshyEvent 1 3 5))
				(if (== self theBush6)
					(curRoom setScript: sEvent1_3_5Room)
				else
					(super doVerb: theVerb)
				)
			)
			((and (== theVerb 4) (OneOf leshyEvent 2 6))
				(if (== self theBush6)
					(= local15 1)
					(bush1 doVerb: theVerb)
				else
					(super doVerb: theVerb)
				)
			)
			(else (bush1 doVerb: theVerb))
		)
	)
)

(instance bush5 of Actor
	(properties
		noun 13
		approachX 182
		approachY 160
		x 182
		y 167
		view 579
		signal $4000
		moveSpeed 0
	)
	
	(method (doVerb theVerb)
		(cond 
			(
			(and (== theVerb 4) (or (== leshyEvent 4) (== leshyEvent 7)))
				(if (bush5 mover?)
					(messager say: 13 4 61)
				else
					(messager say: 13 4 67)
				)
			)
			((and (== theVerb 4) (OneOf leshyEvent 1 3 5))
				(if (== self theBush6)
					(curRoom setScript: sEvent1_3_5Room)
				else
					(super doVerb: theVerb)
				)
			)
			((and (== theVerb 4) (OneOf leshyEvent 2 6))
				(if (== self theBush6)
					(= local15 1)
					(bush1 doVerb: theVerb)
				else
					(super doVerb: theVerb)
				)
			)
			(else (bush1 doVerb: theVerb))
		)
	)
)

(instance bush6 of Actor
	(properties
		noun 13
		approachX 43
		approachY 160
		x 43
		y 167
		view 579
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(cond 
			(
				(and
					(or (== leshyEvent 4) (== leshyEvent 7))
					(== theVerb 4)
					(not (s4Pop client?))
				)
				(if (== (sRunFarBush register?) 6)
					(self setScript: s4Pop)
					(sRunFarBush changeState: 0)
				else
					(super doVerb: theVerb)
				)
			)
			((and (== theVerb 4) (OneOf leshyEvent 1 3 5))
				(if (== self theBush6)
					(curRoom setScript: sEvent1_3_5Room)
				else
					(super doVerb: theVerb)
				)
			)
			((and (== theVerb 4) (OneOf leshyEvent 2 6))
				(if (== self theBush6)
					(= local15 1)
					(bush1 doVerb: theVerb)
				else
					(super doVerb: theVerb)
				)
			)
			(else (bush1 doVerb: theVerb))
		)
	)
)

(instance sBushWiggle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles (+ (ego cycleSpeed?) 2))
			)
			(1
				(if (DoSound sndGET_AUDIO_CAPABILITY)
					(switch global455
						(1 (fx1 number: 998 play:))
						(2 (fx1 number: 1001 play:))
						(else  (fx1 number: 1002 play:))
					)
					(= cycles 1)
				else
					(switch global455
						(1 (messager say: 3 6 5 0 self))
						(2 (messager say: 3 6 7 0 self))
						(else 
							(messager say: 3 6 3 0 self)
						)
					)
				)
			)
			(2
				(theBush6
					signal: (| (theBush6 signal?) $0001)
					setCycle: ForwardCounter 6 self
				)
			)
			(3
				(theBush6 signal: (& (theBush6 signal?) $fffe))
				(= seconds (Random 6 12))
			)
			(4 (self changeState: 0))
		)
	)
)

(instance sEvent1_3_5Room of Script
	(properties
		name "sEvent1&3&5Room"
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(bush1 setScript: 0)
				(if (DoSound sndGET_AUDIO_CAPABILITY)
					(switch global455
						(1 (fx1 number: 1000 play:))
						(2 (fx1 number: 999 play:))
						(else  (fx1 number: 1003 play:))
					)
					(= ticks 75)
				else
					(switch global455
						(1 (messager say: 3 6 6 0 self))
						(2 (messager say: 3 6 8 0 self))
						(else 
							(messager say: 3 6 4 0 self)
						)
					)
				)
			)
			(1
				(ego view: 2 setCel: 0 setCycle: 0)
				(= cycles (+ (ego cycleSpeed?) 2))
			)
			(2
				(fx1 number: 998 play:)
				(= cycles 30)
			)
			(3
				(ego normalize:)
				(= cycles (+ (ego cycleSpeed?) 2))
			)
			(4
				(heroTeller dispose:)
				(theGame handsOff:)
				(switch leshyEvent
					(1
						(if (& msgType $0001)
							(= local18 1)
						else
							(= msgType (| msgType $0001))
						)
						(if (& msgType $0002)
							(= msgType (^ msgType $0002))
							(= local19 1)
						)
						(messager say: 3 6 13 0 self)
						(heroTeller1 init: ego 579 7 128 4)
					)
					(3
						(messager say: 3 6 17 0 self)
						(heroTeller3 init: ego 579 7 128 8)
					)
					(else 
						(messager say: 3 6 31 0 self)
						(heroTeller5 init: ego 579 7 128 10)
					)
				)
			)
			(5
				(theGame handsOff:)
				(switch leshyEvent
					(1
						(if (not local18) (= msgType (^ msgType $0001)))
						(if local19 (= msgType (| msgType $0002)))
						(heroTeller1 doVerb: 2)
					)
					(3 (heroTeller3 doVerb: 2))
					(else  (heroTeller5 doVerb: 2))
				)
			)
			(6
				(theGame handsOff:)
				(if (ego actions?) ((ego actions?) clean:))
				(if (!= bush1 theBush6)
					(theMusic2 number: 939 setLoop: 1 play:)
					(bush1
						signal: (| (bush1 signal?) $0001)
						setLoop: (+ (bush1 loop?) 2) 1
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(7
				(if (!= bush1 theBush6)
					(bush1 signal: (& (bush1 signal?) $fffe))
				)
				(if (!= bush2 theBush6)
					(theMusic2 number: 939 setLoop: 1 play:)
					(bush2
						signal: (| (bush2 signal?) $0001)
						setLoop: (+ (bush2 loop?) 2) 1
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(8
				(if (!= bush2 theBush6)
					(bush2 signal: (& (bush2 signal?) $fffe))
				)
				(if (!= bush3 theBush6)
					(theMusic2 number: 939 setLoop: 1 play:)
					(bush3
						signal: (| (bush3 signal?) $0001)
						setLoop: (+ (bush3 loop?) 2) 1
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(9
				(if (!= bush3 theBush6)
					(bush3 signal: (& (bush3 signal?) $fffe))
				)
				(if (!= bush4 theBush6)
					(theMusic2 number: 939 setLoop: 1 play:)
					(bush4
						signal: (| (bush4 signal?) $0001)
						setLoop: (+ (bush4 loop?) 2) 1
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(10
				(if (!= bush4 theBush6)
					(bush4 signal: (& (bush4 signal?) $fffe))
				)
				(if (!= bush5 theBush6)
					(theMusic2 number: 939 setLoop: 1 play:)
					(bush5
						signal: (| (bush5 signal?) $0001)
						setLoop: (+ (bush5 loop?) 2) 1
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(11
				(if (!= bush5 theBush6)
					(bush5 signal: (& (bush5 signal?) $fffe))
				)
				(if (!= bush6 theBush6)
					(theMusic2 number: 939 setLoop: 1 play:)
					(bush6
						signal: (| (bush6 signal?) $0001)
						setLoop: (+ (bush6 loop?) 2) 1
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(12
				(if (!= bush6 theBush6)
					(bush6 signal: (& (bush6 signal?) $fffe))
				)
				(theMusic2 number: 939 setLoop: 1 play:)
				(theBush6
					signal: (| (theBush6 signal?) $0001)
					setLoop: (+ (theBush6 loop?) 2) 1
					setCycle: End self
				)
			)
			(13
				((curRoom obstacles?)
					delete: bushPoly1 bushPoly2 bushPoly3 bushPoly4 bushPoly5 bushPoly6
				)
				(if (or (== register 0) (== register 3))
					(leshy
						signal: (| (leshy signal?) $0001)
						x: (theBush6 x?)
						y: (theBush6 y?)
						setLoop: (localproc_00fa (theBush6 x?) (theBush6 y?)) 1
						setCel: 0
						init:
						setCycle: End self
					)
				else
					(= ticks 30)
				)
				(theBush6 dispose:)
			)
			(14
				(if (cast contains: leshy)
					(leshy signal: (& (leshy signal?) $fffe))
				)
				(switch register
					(0
						(messager say: 3 6 15 0 self)
					)
					(2
						(curRoom setScript: sEvent3Room)
					)
					(3
						(Bset 258)
						(if (Btst 166)
							(messager say: 3 6 32 0 self)
						else
							(messager say: 3 6 33 0 self)
						)
					)
					(else 
						(switch leshyEvent
							(1 (heroTeller1 dispose:))
							(3 (heroTeller3 dispose:))
							(else  (heroTeller5 dispose:))
						)
						(= local17 1)
						(theMusic fade: curRoom)
						(theGame handsOn:)
						(self dispose:)
					)
				)
			)
			(15 (= ticks 30))
			(16
				(leshy
					signal: (| (leshy signal?) $0001)
					setCycle: Beg self
				)
			)
			(17
				(leshy dispose:)
				(switch leshyEvent
					(1 (heroTeller1 dispose:))
					(3 (heroTeller3 dispose:))
					(else  (heroTeller5 dispose:))
				)
				(= local17 1)
				(theMusic fade: curRoom)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent2_6Room of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== theBush6 bush1)
						(local4 at: 0 bush4)
						(local4 at: 1 bush2)
						(local4 at: 2 bush6)
						(local4 at: 3 bush3)
						(local5 at: 0 bushPoly4)
						(local5 at: 1 bushPoly2)
						(local5 at: 2 bushPoly6)
						(local5 at: 3 bushPoly3)
						((curRoom obstacles?) delete: bushPoly1)
					)
					((== theBush6 bush4)
						(local4 at: 0 bush1)
						(local4 at: 1 bush3)
						(local4 at: 2 bush6)
						(local4 at: 3 bush2)
						(local5 at: 0 bushPoly1)
						(local5 at: 1 bushPoly3)
						(local5 at: 2 bushPoly6)
						(local5 at: 3 bushPoly2)
						((curRoom obstacles?) delete: bushPoly4)
					)
					((== theBush6 bush6)
						(local4 at: 0 bush3)
						(local4 at: 1 bush1)
						(local4 at: 2 bush4)
						(local4 at: 3 bush2)
						(local5 at: 0 bushPoly3)
						(local5 at: 1 bushPoly1)
						(local5 at: 2 bushPoly4)
						(local5 at: 3 bushPoly2)
						((curRoom obstacles?) delete: bushPoly6)
					)
				)
				(theBush6
					signal: (| (theBush6 signal?) $0001)
					setLoop: (+ (theBush6 loop?) 2) 1
					setCel: 0
					setCycle: End self
				)
				(theMusic2 number: 939 setLoop: 1 play:)
			)
			(1
				(theBush6 dispose:)
				(= cycles 10)
			)
			(2
				((= theBush6 (local4 at: 0))
					signal: (| (theBush6 signal?) $0001)
					setCycle: Fwd
				)
			)
			(3
				(theBush6
					setLoop: (+ (theBush6 loop?) 2) 1
					setCel: 0
					setCycle: End self
				)
				(theMusic2 number: 939 setLoop: 1 play:)
			)
			(4
				(theBush6 dispose:)
				((curRoom obstacles?) delete: (local5 at: 0))
				((= theBush6 (local4 at: 1))
					signal: (| (theBush6 signal?) $0001)
					setCycle: Fwd
				)
			)
			(5
				(theBush6
					setLoop: (+ (theBush6 loop?) 2) 1
					setCel: 0
					setCycle: End self
				)
				(theMusic2 number: 939 setLoop: 1 play:)
			)
			(6
				(theBush6 dispose:)
				((curRoom obstacles?) delete: (local5 at: 1))
				((= theBush6 (local4 at: 2))
					signal: (| (theBush6 signal?) $0001)
					setCycle: Fwd
				)
			)
			(7
				(theBush6
					setLoop: (+ (theBush6 loop?) 2) 1
					setCel: 0
					setCycle: End self
				)
				(theMusic2 number: 939 setLoop: 1 play:)
			)
			(8
				(theBush6 dispose:)
				((curRoom obstacles?) delete: (local5 at: 2))
				((= theBush6 (local4 at: 3))
					signal: (| (theBush6 signal?) $0001)
					setCycle: Fwd
				)
			)
			(9
				(theBush6
					setLoop: (+ (theBush6 loop?) 2) 1
					setCel: 0
					setCycle: End self
				)
				(theMusic2 number: 939 setLoop: 1 play:)
			)
			(10
				(theBush6 dispose:)
				((curRoom obstacles?) delete: (local5 at: 3))
				((= theBush6 bush5)
					signal: (& (theBush6 signal?) $0001)
					setCycle: Fwd
				)
			)
			(11
				(theGame handsOff:)
				(theBush6
					setLoop: (+ (bush5 loop?) 2) 1
					setCel: 0
					setCycle: End self
				)
				(theMusic2 number: 939 setLoop: 1 play:)
			)
			(12
				(theBush6 dispose:)
				(= theBush6 0)
				((curRoom obstacles?) delete: bushPoly5)
				(leshy
					signal: (| (leshy signal?) $0001)
					x: (bush5 x?)
					y: (bush5 y?)
					setLoop: (localproc_00fa (bush5 x?) (bush5 y?)) 1
					cycleSpeed: (* defaultCycles 2)
					setCel: 0
					setCycle: End self
				)
			)
			(13
				(leshy signal: (& (leshy signal?) $fffe))
				(if (== leshyEvent 2)
					(messager say: 3 6 16 0 self)
				else
					(messager say: 3 6 55 0 self)
				)
			)
			(14
				(heroTeller dispose:)
				(if (== leshyEvent 2)
					(heroTeller2 init: ego 579 7 128 6)
				else
					(heroTeller6 init: ego 579 7 128 11)
				)
				(leshy
					signal: (| (leshy signal?) $0001)
					setCycle: Beg self
				)
			)
			(15
				(leshy x: 1000 signal: (& (leshy signal?) $fffe))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent2_6Room2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< (ego x?) 160)
					(leshy
						x: (+ (bush5 x?) 30)
						setLoop: (localproc_00fa (+ (bush5 x?) 30) (bush5 y?)) 1
					)
				else
					(leshy
						x: (- (bush5 x?) 30)
						setLoop: (localproc_00fa (- (bush5 x?) 30) (bush5 y?)) 1
					)
				)
				(leshy
					signal: (| (leshy signal?) $0001)
					y: (bush5 y?)
					setScript: 0
					cycleSpeed: (* defaultCycles 2)
					setCycle: End self
				)
			)
			(1
				(leshy signal: (& (leshy signal?) $fffe))
				(= ticks 60)
			)
			(2
				(if (== leshyEvent 2)
					(messager say: 3 6 17 0 self)
				else
					(messager say: 3 6 54 0 self)
				)
			)
			(3
				(leshy
					signal: (| (leshy signal?) $0001)
					setCycle: Beg self
				)
			)
			(4
				(leshy dispose:)
				(if (== leshyEvent 2)
					(heroTeller2 dispose:)
				else
					(heroTeller6 dispose:)
				)
				(= local17 1)
				(theMusic fade: curRoom)
				(= cycles 1)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent3Room of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< (ego x?) 160)
					(leshy x: (+ (ego x?) 20) setLoop: 3 1)
				else
					(leshy x: (- (ego x?) 20) setLoop: 2 1)
				)
				(if (not (cast contains: leshy)) (leshy init:))
				(leshy
					signal: (| (leshy signal?) $0001)
					show:
					y: (bush5 y?)
					setScript: 0
					setCel: 0
					cycleSpeed: (* defaultCycles 2)
					setCycle: End self
				)
			)
			(1
				(messager say: 3 6 66 0 self)
			)
			(2 (leshy setCycle: Beg self))
			(3
				(if (== (ego actions?) heroTeller3)
					(heroTeller3 dispose:)
				)
				(leshy dispose:)
				(theMusic fade:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sRunFarBush of Script
	(properties)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 egoX egoY)
		(super doit: &rest)
		(if (== state 3)
			(= egoX (ego x?))
			(= egoY (ego y?))
			(switch register
				(1
					(= temp0 (local6 at: 0))
					(= temp1 (local6 at: 2))
					(= temp2 (local6 at: 1))
					(= temp3 (local6 at: 5))
					(if (and (< temp0 egoX) (< egoX temp1))
						(bush5 x: (+ (- temp1 egoX) temp0))
					)
					(if (and (< temp2 egoY) (< egoY temp3))
						(bush5 y: (+ (- temp3 egoY) temp2))
					)
				)
				(3
					(= temp0 (local8 at: 0))
					(= temp1 (local8 at: 2))
					(= temp2 (local8 at: 1))
					(= temp3 (local8 at: 5))
					(if (and (< temp0 egoX) (< egoX temp1))
						(bush5 x: (+ (- temp1 egoX) temp0))
					)
					(if (and (< temp2 egoY) (< egoY temp3))
						(bush5 y: (+ (- temp3 egoY) temp2))
					)
				)
				(4
					(= temp0 (local9 at: 0))
					(= temp1 (local9 at: 2))
					(= temp2 (local9 at: 1))
					(= temp3 (local9 at: 5))
					(if (and (< temp0 egoX) (< egoX temp1))
						(bush5 x: (+ (- temp1 egoX) temp0))
					)
					(if (and (< temp2 egoY) (< egoY temp3))
						(bush5 y: (+ (- temp3 egoY) temp2))
					)
				)
				(6
					(= temp0 (local11 at: 0))
					(= temp1 (local11 at: 2))
					(= temp2 (local11 at: 1))
					(= temp3 (local11 at: 5))
					(if (and (< temp0 egoX) (< egoX temp1))
						(bush5 x: (+ (- temp1 egoX) temp0))
					)
					(if (and (< temp2 egoY) (< egoY temp3))
						(bush5 y: (+ (- temp3 egoY) temp2))
					)
				)
			)
			(cond 
				((!= (bush5 x?) bush5X) (= bush5X (bush5 x?)) (bush5 setCel: (Random 0 4)))
				((!= (bush5 y?) bush5Y) (= bush5Y (bush5 y?)) (bush5 setCel: (Random 0 4)))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch prevRoomNum
					(571
						(switch (++ local12)
							(1 (= register 4))
							(2 (= register 1))
							(3 (= register 3))
							(4 (= register 6))
						)
					)
					(578
						(switch (++ local12)
							(1 (= register 6))
							(2 (= register 3))
							(3 (= register 1))
							(4 (= register 4))
						)
					)
					(585
						(switch (++ local12)
							(1 (= register 1))
							(2 (= register 4))
							(3 (= register 6))
							(4 (= register 3))
						)
					)
				)
				(bush5
					signal: (| (bush5 signal?) $0001)
					setCycle: Fwd
					setStep: 5 5
					approachVerbs: 0
				)
				(switch register
					(1
						(bush5 setMotion: PolyPath 0 108 self)
					)
					(3
						(bush5 setMotion: PolyPath 269 97 self)
					)
					(4
						(bush5 setMotion: PolyPath 319 166 self)
					)
					(6
						(bush5 setMotion: PolyPath 0 184 self)
					)
				)
			)
			(1
				(if (< local12 5) (messager say: 3 6 28 0 self))
			)
			(2 (bush5 setCycle: End self))
			(3 (theGame handsOn:))
		)
	)
)

(instance s4Pop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local12 5) (theGame handsOff:))
				(theMusic2 number: 939 setLoop: 1 play:)
				(client
					signal: (| (client signal?) $0001)
					setLoop: (+ (client loop?) 2) 1
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(client hide: signal: (& (client signal?) $fffe))
				(switch client
					(bush1
						((curRoom obstacles?) delete: bushPoly1)
					)
					(bush3
						((curRoom obstacles?) delete: bushPoly3)
					)
					(bush4
						((curRoom obstacles?) delete: bushPoly4)
					)
					(bush6
						((curRoom obstacles?) delete: bushPoly6)
					)
				)
				(if (== local12 5)
					(bush5
						signal: (| (bush5 signal?) $0001)
						setCycle: Fwd
						cycleSpeed: 6
						setMotion:
							MoveTo
							(if (< (bush5 x?) 160)
								(+ (bush5 x?) 30)
							else
								(- (bush5 x?) 30)
							)
							(bush5 y?)
							self
					)
				else
					(self dispose:)
				)
			)
			(2
				(bush5
					setScript: 0
					setCycle: Fwd
					setMotion:
						MoveTo
						(if (< (bush5 x?) 160)
							(- (bush5 x?) 10)
						else
							(+ (bush5 x?) 10)
						)
						(bush5 y?)
						self
				)
			)
			(3
				(messager say: 3 6 29 0 self)
			)
			(4
				(bush5
					setLoop: (+ (bush5 loop?) 2) 1
					setCel: 0
					setCycle: End self
				)
				(theMusic2 number: 939 setLoop: 1 play:)
			)
			(5
				(bush5 hide: signal: (& (bush5 signal?) $fffe))
				(leshy
					signal: (| (leshy signal?) $0001)
					x: (bush5 x?)
					y: (bush5 y?)
					setLoop: (localproc_00fa (bush5 x?) (bush5 y?)) 1
					setCel: 0
					init:
					setCycle: End self
				)
			)
			(6
				(leshy signal: (& (leshy signal?) $fffe))
				(= ticks 120)
			)
			(7
				(heroTeller dispose:)
				(if (== leshyEvent 4)
					(messager say: 3 6 30 0 self)
					(heroTeller4 init: ego 579 7 128 9)
				else
					(if (== heroType 1)
						(messager say: 3 6 56 0 self)
					else
						(messager say: 3 6 57 0 self)
					)
					(heroTeller7 init: ego 579 7 128 12)
				)
			)
			(8 (= ticks 120))
			(9
				(leshy
					signal: (| (leshy signal?) $0001)
					setCycle: End self
				)
			)
			(10 (leshy setCycle: Beg self))
			(11
				(leshy dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance s4_7DoRiddle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (cast contains: leshy)) (leshy init:))
				(leshy
					signal: (| (leshy signal?) $0001)
					show:
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(if (== leshyEvent 4)
					(messager say: 3 6 31 0 self)
					(heroTeller4 dispose:)
					(Bset 186)
				else
					(messager say: 3 6 59 0 self)
					(heroTeller7 dispose:)
					(Bset 260)
				)
			)
			(2 (leshy setCycle: Beg self))
			(3
				(leshy dispose:)
				(theMusic fade:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLastEvent of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (not state) (< (ego distanceTo: bush5 55) 55))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (bush5 setCycle: Fwd))
			(1
				(theGame handsOff:)
				(ego setMotion: 0)
				(fx1 number: 995 play:)
				(bush1
					signal: (| (bush1 signal?) $0001)
					setStep: 6 4
					setMotion: JumpTo (ego x?) (ego y?)
				)
				(= cycles 2)
			)
			(2
				(fx2 number: 995 play:)
				(bush3
					signal: (| (bush1 signal?) $0001)
					setStep: 6 4
					setMotion: JumpTo (ego x?) (ego y?)
				)
				(= cycles 2)
			)
			(3
				(fx3 number: 995 play:)
				(bush4
					signal: (| (bush1 signal?) $0001)
					setStep: 6 4
					setMotion: JumpTo (ego x?) (ego y?)
				)
				(= cycles 2)
			)
			(4
				(fx4 number: 995 play:)
				(bush6
					signal: (| (bush1 signal?) $0001)
					setStep: 6 4
					setMotion: JumpTo (ego x?) (ego y?)
				)
				(= cycles 20)
			)
			(5
				(fx1 number: 996 play:)
				(= register (ego cycleSpeed?))
				(ego
					view: 6
					setLoop: 4 1
					setCel: 0
					cycleSpeed: defaultCycles
					setCycle: End self
				)
			)
			(6
				(fx2 number: 1000 play:)
				(bush1 signal: (& (bush1 signal?) $fffe))
				(bush3 signal: (& (bush3 signal?) $fffe))
				(bush4 signal: (& (bush4 signal?) $fffe))
				(bush5 signal: (& (bush5 signal?) $fffe))
				(bush6 signal: (& (bush6 signal?) $fffe))
				(= ticks 60)
			)
			(7
				(messager say: 3 6 58 0 self)
			)
			(8 (= ticks 60))
			(9
				(Bset 255)
				(theMusic2 number: 939 setLoop: 1 play:)
				(bush5
					signal: (| (bush5 signal?) $0001)
					setLoop: (+ (bush5 loop?) 2) 1
					setCycle: End self
				)
				(bush1
					signal: (| (bush1 signal?) $0001)
					setLoop: (+ (bush1 loop?) 2) 1
					setCycle: End
				)
				(bush3
					signal: (| (bush3 signal?) $0001)
					setLoop: (+ (bush3 loop?) 2) 1
					setCycle: End
				)
				(bush4
					signal: (| (bush4 signal?) $0001)
					setLoop: (+ (bush4 loop?) 2) 1
					setCycle: End
				)
				(bush6
					signal: (| (bush6 signal?) $0001)
					setLoop: (+ (bush6 loop?) 2) 1
					setCycle: End
				)
			)
			(10 (= ticks 120))
			(11
				(if ((curRoom obstacles?) contains: bushPoly1)
					((curRoom obstacles?) delete: bushPoly1)
				)
				(if ((curRoom obstacles?) contains: bushPoly2)
					((curRoom obstacles?) delete: bushPoly2)
				)
				(if ((curRoom obstacles?) contains: bushPoly3)
					((curRoom obstacles?) delete: bushPoly3)
				)
				(if ((curRoom obstacles?) contains: bushPoly4)
					((curRoom obstacles?) delete: bushPoly4)
				)
				(if ((curRoom obstacles?) contains: bushPoly5)
					((curRoom obstacles?) delete: bushPoly5)
				)
				(if ((curRoom obstacles?) contains: bushPoly6)
					((curRoom obstacles?) delete: bushPoly6)
				)
				(bush1 signal: (& (bush1 signal?) $fffe))
				(bush3 signal: (& (bush3 signal?) $fffe))
				(bush4 signal: (& (bush4 signal?) $fffe))
				(bush5 signal: (& (bush5 signal?) $fffe))
				(bush6 signal: (& (bush6 signal?) $fffe))
				(ego setLoop: 6 1 setCel: 0 setCycle: End self)
			)
			(12
				(ego setSpeed: register normalize: 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fx1 of Sound
	(properties)
)

(instance fx2 of Sound
	(properties)
)

(instance fx3 of Sound
	(properties)
)

(instance fx4 of Sound
	(properties)
)
