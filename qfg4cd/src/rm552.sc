;;; Sierra Script 1.0 - (do not remove this comment)
(script# 552)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use TellerIcon)
(use ForestView)
(use PChase)
(use PolyPath)
(use Polygon)
(use Sound)
(use Motion)
(use User)
(use System)

(public
	rm552 0
)

(local
	local0
)
(instance rm552 of GloryRm
	(properties
		picture 410
		horizon 70
		north 600
		east 557
		south 553
		topX 181
		rightY 131
	)
	
	(method (init)
		(if debugging (= Night 1) (Bset 34))
		(self setRegions: 50)
		(if
		(and Night (Btst 34) (not (Btst 110)) (not global365))
			(Bset 35)
			(self setRegions: 51)
			(adAvis init: setScale: 140 approachVerbs: 4)
			(adTeller init: adAvis 51 4 160 2)
			(egoTeller init: ego 51 4 128 2)
			((ScriptID 50 1) caller: adAvis)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							66
							155
							49
							160
							103
							189
							0
							189
							0
							0
							319
							0
							319
							118
							247
							118
							201
							90
							163
							90
							99
							105
							111
							134
							54
							134
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 215 143 319 143 319 189 240 189 200 167
						yourself:
					)
			)
			(Bset 380)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							66
							155
							49
							160
							103
							189
							0
							189
							0
							0
							127
							0
							127
							72
							164
							90
							99
							105
							111
							134
							54
							134
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 215 143 319 143 319 189 240 189 200 167
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 240 72 240 0 319 0 319 120 254 120 199 93
						yourself:
					)
			)
		)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 90 116 140 116 158 129 130 140 82 128
					yourself:
				)
				60
				((Polygon new:)
					init: 88 189 68 170 161 130 149 118 164 117 203 155 135 176 176 189
					yourself:
				)
				130
				((Polygon new:)
					init: 185 189 147 174 218 153 278 189
					yourself:
				)
				60
		)
		(atp0 init:)
		(atp1 init:)
		(atp3 init: setPri: 169)
		(atp4 init:)
		(if Night (atp5 init:))
		(path init:)
		(if (Btst 380) (theGame save: 1))
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			((and (Btst 35) (<= (ego y?) 92)) (curRoom setScript: sSummoning))
		)
	)
	
	(method (cue)
		(if (== (sSummoning state?) 6)
			(sSummoning cue:)
		else
			(adAvis getHurt:)
		)
	)
	
	(method (notify param1)
		(if
			(and
				(== param1 1)
				(!= (curRoom script?) (ScriptID 51 4))
			)
			(curRoom setScript: (ScriptID 51 4))
		)
	)
)

(instance adTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 74))
	)
	
	(method (respond)
		(return
			(if (== (++ local0) 4)
				(self clean:)
				(curRoom setScript: sSummoning)
			else
				(super respond: &rest)
				(return 1)
			)
		)
	)
)

(instance egoTeller of Teller
	(properties
		loopMenu 0
	)
	
	(method (doVerb theVerb)
		(if (== (++ local0) 4)
			(= loopMenu 0)
			(curRoom setScript: sSummoning)
		else
			(super doVerb: theVerb)
		)
		(return 1)
	)
	
	(method (respond)
		(super respond: &rest)
		(if (== iconValue -999) ((User curEvent?) claimed: 1))
		(return 1)
	)
)

(instance sControlEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 36)
				(ego setMotion: 0)
				(messager say: 1 6 8 0 self 51)
			)
			(1
				(curRoom setScript: (ScriptID 51 4))
			)
		)
	)
)

(instance sSummoning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(messager say: 2 6 4 0 self 51)
			)
			(2
				(adAvis
					signal: (| (adAvis signal?) $0001)
					setCycle: End self
				)
			)
			(3
				(soundFX number: 131 setLoop: 1 play:)
				(messager say: 2 6 5 0 self 51)
				(adAvis setCel: 0 signal: (& (adAvis signal?) $fffe))
			)
			(4
				(theMusic number: 103 setLoop: -1 play:)
				((ScriptID 51 1)
					init:
					x: 181
					y: 60
					approachDist: 1
					setMotion: PolyPath (+ (adAvis x?) 30) (- (adAvis y?) 10) self
				)
				((ScriptID 51 2) init: x: 350 y: 100)
			)
			(5
				((ScriptID 51 1) setHeading: 180)
				((ScriptID 51 2) setMotion: PolyPath 285 100 self)
			)
			(6
				(messager say: 2 6 6 0 0 51)
				(= seconds 5)
			)
			(7
				(messager say: 2 6 7 0 0 51)
				((ScriptID 51 1)
					setMotion: PChase ego 25 (ScriptID 51 1)
				)
				((ScriptID 51 2)
					setMotion: PChase ego 25 (ScriptID 51 2)
				)
			)
		)
	)
)

(instance moveFarther of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath (- (ego x?) 50) (ego y?) self)
			)
			(1
				(if (Btst 20)
					(messager say: 1 6 1 0 self 51)
				else
					(self cue:)
				)
			)
			(2
				(messager say: 2 6 2 0 self 51)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance adAvis of TargProp
	(properties
		noun 2
		modNum 51
		x 117
		y 113
		view 677
		signal $4000
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(!= (curRoom script?) sControlEgo)
				(!= (curRoom script?) (ScriptID 51 4))
				(< (ego distanceTo: self) 40)
			)
			(theGame handsOff:)
			(self setScript: 0)
			(curRoom setScript: sControlEgo)
		)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				36
				20
				21
				85
				83
				81
				87
				86
				88
				79
				102
				91
				89
				93
				80
				90
				94
				92
				82
				84
				95
				96
				97
				98
				11
			)
			((ScriptID 51) doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(theGame handsOff:)
		(if (== prevRoomNum 557)
			(moveFarther start: 0)
		else
			(moveFarther start: 1)
		)
		(curRoom setScript: moveFarther)
	)
	
	(method (getHurt)
		(if (not script) (self setScript: sSummoning))
	)
)

(instance atp0 of ForestView
	(properties
		x 94
		y 49
		view 413
		cel 1
		signal $4001
	)
)

(instance atp1 of ForestView
	(properties
		y 108
		view 413
		signal $4001
	)
)

(instance atp3 of ForestView
	(properties
		x 210
		y 119
		priority 12
		view 414
		cel 3
		signal $4001
	)
)

(instance atp4 of ForestView
	(properties
		x 155
		y 91
		view 415
		cel 5
		signal $4001
	)
)

(instance atp5 of ForestView
	(properties
		x 61
		y 189
		view 418
		cel 1
		signal $4001
	)
)

(instance path of ForestView
	(properties
		x 172
		y 73
		priority 1
		fixPriority 1
		view 410
		loop 2
		signal $4001
	)
)

(instance soundFX of Sound
	(properties)
)
