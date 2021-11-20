;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use Intrface)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm600 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10
)
(instance rm600 of GloryRm
	(properties
		noun 1
		picture 600
		north 610
		south 552
		bottomX 150
	)
	
	(method (init)
		(theGame handsOff:)
		(theMusic doSongs: stop:)
		(if debugging
			(cond 
				((== (= local0 (GetNumber {Event #?})) 1) (= prevRoomNum 630))
				((OneOf local0 2 3 4 5 6 7 8 9 10) (if (== local0 7) (Bset 122)) (= prevRoomNum 552))
				((== local0 11) (= prevRoomNum 670))
			)
		else
			(= local0
				(cond 
					(
						(and
							(== prevRoomNum 552)
							(< 4 timeODay)
							(< timeODay 6)
							(Btst 124)
							(Btst 395)
						)
						12
					)
					((== prevRoomNum 670) 11)
					(
						(and
							(OneOf prevRoomNum 552 670)
							(Btst 110)
							(or (ego has: 52) (Btst 323))
							(or (ego has: 53) (Btst 324))
							(or (ego has: 55) (Btst 325))
							(or (ego has: 56) (Btst 326))
							(or (ego has: 54) (Btst 327))
						)
						10
					)
					((== prevRoomNum 610) 1)
					(
						(and
							(== prevRoomNum 552)
							(<= 4 timeODay)
							(<= timeODay 6)
							(< gCurrentDay_5 (- Day 2))
							(Btst 124)
							(not (Btst 395))
							(not (Btst 115))
						)
						9
					)
					(
						(and
							(== prevRoomNum 552)
							Night
							(<= 4 timeODay)
							(<= timeODay 6)
							(< gCurrentDay_5 Day)
							(Btst 82)
							(not (Btst 124))
							(not (Btst 115))
						)
						8
					)
					(
						(and
							(== prevRoomNum 552)
							(Btst 122)
							(not Night)
							(not (Btst 123))
						)
						7
					)
					(
					(and (not Night) (< gCurrentDay_12 Day) (Btst 121)) 6)
					(
					(and (== gCurrentDay_12 Day) (Btst 121) (not Night)) 5)
					((and (not (Btst 120)) (not Night)) 2)
					(
						(and
							(< gCurrentDay_12 Day)
							(not (Btst 121))
							(not Night)
						)
						4
					)
					(
					(and (Btst 120) (== gCurrentDay_12 Day) (not Night)) 3)
					(else 0)
				)
			)
		)
		(if
		(or (not (== local0 11)) (not (== local0 10)))
			(ego init: normalize: setScaler: Scaler 130 45 189 109)
		)
		(if
		(or (OneOf prevRoomNum 810 610 630) (== local0 10))
			(aGate posn: -30 105 setPri: 163 init:)
		else
			(aGate init: approachVerbs: 4)
			(cond 
				((== local0 10) (aGate posn: -30 105))
				((not (Btst 122))
					(if Night
						(gateTeller init: aGate 600 14 125 15)
					else
						(gateTeller init: aGate 600 14 125 13)
					)
				)
			)
		)
		(if
		(or (OneOf prevRoomNum 670 810 610 630) (== local0 10))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							275
							163
							255
							151
							225
							151
							217
							159
							200
							159
							193
							166
							122
							166
							107
							151
							116
							134
							193
							118
							215
							109
							226
							114
							229
							110
							227
							107
							225
							110
							214
							105
							159
							118
							140
							104
							95
							114
							48
							134
							26
							155
							78
							144
							80
							164
							61
							174
							100
							189
							-300
							189
							-300
							0
							619
							0
							619
							189
							236
							189
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							59
							169
							62
							175
							99
							189
							0
							189
							0
							0
							619
							0
							619
							189
							236
							189
							275
							163
							255
							151
							223
							151
							216
							158
							201
							159
							194
							166
							122
							166
							115
							160
						yourself:
					)
			)
		)
		(switch prevRoomNum
			(670
				(= Night 1)
				(= timeODay 6)
				((ScriptID 7 4) init: 24 0)
				(curRoom setScript: sEvent11)
			)
			(810
				(cond 
					((== battleResult 1)
						(sndNecRoar play:)
						(ego
							view: 43
							setLoop: 2 1
							setCel: 0
							posn: 100 147
							setCycle: End
						)
						(pNec2
							view: 872
							posn: (- (ego x?) 10) (- (ego y?) 12)
							setCel: 0
							init:
							ignoreActors: 1
						)
						(if (Btst 119)
							(pNec1 view: 872 posn: 42 128 setCel: 5 init:)
							(curRoom setScript: sOneDead)
						else
							(pNec1
								view: 872
								setLoop: 1 1
								posn: (+ (ego x?) 10) (+ (ego y?) 12)
								setCel: 0
								init:
								ignoreActors: 1
							)
							(curRoom setScript: sDead)
						)
					)
					((not (Btst 119))
						(sndNecRoar play:)
						(Bset 119)
						(ego posn: 107 152)
						(pNec1
							view: 872
							setCel: 0
							posn: 42 128
							init:
							setCycle: End
						)
						(pNec2 init:)
						(curRoom setScript: sGoToCombat)
					)
					(else
						(Bset 102)
						(Bset 122)
						(ego posn: 107 152)
						(pNec1
							view: 872
							setLoop: 0 1
							setCel: 5
							posn: 42 128
							init:
						)
						(pNec2
							view: 872
							setCel: 0
							setLoop: 0 1
							posn: 246 158
							init:
							setCycle: End
						)
						(theGame handsOn:)
					)
				)
			)
			(610
				(ego x: 225 y: 108 setScript: sFromCastle)
			)
			(630
				(ego x: 225 y: 108 setScript: sFromCastle)
			)
			(else 
				(ego x: 100 y: 250 setScript: sFromSouth)
				(switch local0
					(2
						(curRoom setScript: sEvent2)
						(= local2 1)
					)
					(3
						(curRoom setScript: sEvent3)
						(= local2 1)
					)
					(4
						(curRoom setScript: sEvent4)
						(= local2 2)
					)
					(5
						(curRoom setScript: sEvent5)
						(= local2 2)
					)
					(6
						(curRoom setScript: sEvent6)
						(= local2 3)
					)
					(7 (curRoom setScript: sEvent7))
					(8
						(aKatrina init: approachVerbs: 4)
						(katrinaTeller init: aKatrina 600 14 127 21)
						(heroTeller init: ego 600 14 128 21)
						(= local7 1)
						(curRoom setScript: sEvent8)
					)
					(9
						(aKatrina init: approachVerbs: 4)
						(katrinaTeller init: aKatrina 600 14 127 22)
						(heroTeller init: ego 600 14 128 22)
						(= local7 1)
						(Bset 395)
						(curRoom setScript: sEvent9)
					)
					(10
						(fStop init:)
						(curRoom setScript: sEvent10)
					)
				)
			)
		)
		(if
			(and
				(!= gCurrentDay_12 Day)
				(not (== prevRoomNum 810))
			)
			(Bclr 119)
		)
		(if
			(and
				(not (Btst 119))
				(not (OneOf local0 2 3 4 5 6 7 10))
				(or (== prevRoomNum 552) (== prevRoomNum 670))
				Night
			)
			(pNec1 init:)
			(pNec2 init: setScript: sRandomRoars)
		)
		(cond 
			((OneOf local0 8 9) (theMusic number: 110 setLoop: -1 play:))
			(Night (theMusic number: 557 loop: -1 play:))
			(else (theMusic number: 558 loop: -1 play:))
		)
		(if (not (== local0 10))
			(specialEFtr
				nsLeft: 0
				nsTop: 187
				nsBottom: 189
				nsRight: 248
				approachX: 150
				approachY: 189
				init:
			)
		)
		(if
			(and
				(OneOf local0 2 3 4 5 6 7)
				(not (OneOf prevRoomNum 610 630))
			)
			(heroTeller
				init:
					ego
					600
					14
					128
					(if (Btst 122)
						20
					else
						(switch local2
							(1 17)
							(2 18)
							(3 19)
						)
					)
			)
			(= local1 1)
			(gateKeeper
				init:
				setScript: sKeeperIncidental
				approachVerbs: 4 2
			)
			(keeperTeller
				init:
					gateKeeper
					600
					14
					153
					(if (Btst 122)
						20
					else
						(switch local2
							(1 17)
							(2 18)
							(3 19)
						)
					)
			)
		else
			(fTowerWindow init: approachVerbs: 4)
		)
		(super init: &rest)
		(fNextRoom init: approachVerbs: 4)
		(fCastle init: approachVerbs: 4)
		(fRoad init: approachVerbs: 4)
		(fRoad2 init: approachVerbs: 4)
		(fTree init: approachVerbs: 4)
		(fParapet init: approachVerbs: 4)
		(fTower init: approachVerbs: 4)
		(fArchWay init: approachVerbs: 4)
		(if (and (Btst 380) (!= prevRoomNum 810))
			(theGame save: 1)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== (curRoom script?) (ScriptID 31 1))
				(>= (ego z?) 124)
			)
			(curRoom setScript: sLevitating)
		)
	)
	
	(method (dispose)
		(theMusic fade: 0)
		(zappedSound dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(89
					(ego trySkill: 29)
					(cond 
						(Night ((ScriptID 31 0) init: 108 166 125 0 3))
						(local1 (messager say: 2 6 8))
						(else (messager say: 12 6 7))
					)
				)
				(1
					(cond 
						((and (!= local0 10) (not Night)) (messager say: 1 1 3) (return 1))
						((and (!= local0 10) Night) (messager say: 1 1 2) (return 1))
						(else (super doVerb: theVerb))
					)
				)
				(83
					(= projX ((User curEvent?) x?))
					(= projY ((User curEvent?) y?))
					(if local7
						(messager say: 12 6 28 0)
					else
						(ego trySkill: 12)
						(= local5 3)
						(curRoom setScript: sThrowIt)
					)
				)
				(86
					(= projX ((User curEvent?) x?))
					(= projY ((User curEvent?) y?))
					(if local7
						(messager say: 12 6 28 0)
					else
						(ego trySkill: 12)
						(= local5 5)
						(curRoom setScript: sThrowIt)
					)
				)
				(88
					(= projX ((User curEvent?) x?))
					(= projY ((User curEvent?) y?))
					(if local7
						(messager say: 12 6 28 0)
					else
						(ego trySkill: 12)
						(= local5 6)
						(curRoom setScript: sThrowIt)
					)
				)
				(91
					(= projX ((User curEvent?) x?))
					(= projY ((User curEvent?) y?))
					(if local7
						(messager say: 12 6 28 0)
					else
						(ego trySkill: 12)
						(= local5 7)
						(curRoom setScript: sThrowIt)
					)
				)
				(93
					(= projX ((User curEvent?) x?))
					(= projY ((User curEvent?) y?))
					(if local7
						(messager say: 12 6 28 0)
					else
						(ego trySkill: 12)
						(= local5 9)
						(curRoom setScript: sThrowIt)
					)
				)
				(81
					(ego trySkill: 21)
					(messager say: 0 81 0 0)
				)
				(21
					(self setScript: (ScriptID 32) 0 21)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
	
	(method (leaveRoom &tmp temp0)
		(if local1
			(switch local0
				(2 (messager say: 2 6 30))
				(3 (DailyMsg 2 6 42 0 600))
				(4 (messager say: 2 6 31))
				(5 (DailyMsg 2 6 42 0 600))
				(6 (DailyMsg 2 6 42 0 600))
				(8
					(switch (= temp0 (Random 1 5))
						(1 (messager say: 16 6 42 1))
						(2 (messager say: 16 6 42 2))
						(3 (messager say: 16 6 42 3))
						(4 (messager say: 16 6 42 4))
						(5 (messager say: 16 6 42 5))
					)
					(= gCurrentDay_5 Day)
				)
				(9
					(switch (= temp0 (Random 1 5))
						(1 (messager say: 16 6 42 1))
						(2 (messager say: 16 6 42 2))
						(3 (messager say: 16 6 42 3))
						(4 (messager say: 16 6 42 4))
						(5 (messager say: 16 6 42 5))
					)
					(= gCurrentDay_5 Day)
				)
			)
		)
		(Bclr 102)
		(= gCurrentDay_12 Day)
		(super leaveRoom:)
	)
)

(instance sRandomRoars of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (self cue:))
			(1 (= seconds (Random 1 20)))
			(2 (sndNecRoar play: self))
			(3
				(sndNecRoar stop:)
				(self changeState: 0)
			)
		)
	)
)

(instance sLevitating of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScaler: Scaler 85 80 110 0 posn: 104 38 setCel: 5)
				(= ticks 30)
			)
			(1
				(ego setPri: 86 setCycle: 0 setMotion: JumpTo 88 148 self)
			)
			(2
				(if (or (== local0 10) (Btst 119))
					(ego
						normalize:
						setLoop: 7
						posn: 88 143
						setScaler: Scaler 130 45 189 109
					)
					(theGame handsOn:)
					(self dispose:)
				else
					(ego
						view: 43
						setLoop: 2 1
						setCel: 0
						posn: 98 145
						setCycle: 0
					)
					(pNec1
						view: 870
						setLoop: 0 1
						setCel: 0
						setCycle: Walk
						setMotion: MoveTo 53 145 self
					)
				)
			)
			(3 (ego setCycle: End self))
			(4 (EgoDead 10 600 0 0 912))
		)
	)
)

(instance sStop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 12)
			)
			(1
				(messager say: 12 6 25 0 self)
			)
			(2
				(ego setMotion: PolyPath 121 181 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKatrinaWalksAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 5)
			)
			(1
				(katrinaTeller dispose:)
				(if (== local0 8)
					(messager say: 16 6 36 1 self)
				else
					(messager say: 16 6 38 1 self)
				)
			)
			(2
				(aKatrina
					setLoop: 1 1
					setCel: 0
					setCycle: Fwd
					setMotion: MoveTo -20 180 self
				)
			)
			(3
				(aKatrina hide: dispose:)
				(heroTeller dispose:)
				(if (== local0 8)
					(messager say: 12 6 26 1 self)
				else
					(messager say: 12 6 40 1 self)
				)
			)
			(4
				(= gCurrentDay_5 Day)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sThrowIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch local5
					(1
						(if (== (ego has: 5) 1)
							(messager say: 12 6 116)
						else
							(self setScript: (ScriptID 32) self 37)
						)
					)
					(2
						(self setScript: (ScriptID 32) self 21)
					)
					(5
						(self setScript: (ScriptID 32) self 86)
					)
					(6
						(self setScript: (ScriptID 32) self 88)
					)
					(7
						(self setScript: (ScriptID 62) self)
					)
					(8
						(self setScript: (ScriptID 32) self 93)
					)
					(else  (self cue:))
				)
			)
			(1
				(ego normalize:)
				(= seconds 2)
			)
			(2
				(if (OneOf local5 1 2)
					(messager say: 12 6 6 0 self)
				else
					(messager say: 12 6 7 0 self)
				)
			)
			(3
				(= local5 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbTheGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 108 166 self)
			)
			(1 (Face ego 123 168 self))
			(2
				(ego
					view: 7
					setLoop: 3 1
					setCel: 0
					posn: 109 163
					setCycle: Fwd
					ignoreActors: 1
					setPri: 174
					setScaler: Scaler 114 77 166 30
					setMotion: MoveTo 108 91 self
				)
			)
			(3
				(ego
					setLoop: 4 1
					setCel: 0
					posn: 106 38
					setCycle: End self
				)
			)
			(4
				(ego
					view: 58
					setLoop: 0 1
					setCel: 0
					posn: 99 39
					setPri: 53
					setCycle: CT 1 1 self
				)
			)
			(5
				(ego setMotion: JumpTo 93 140 self)
			)
			(6 (ego setCycle: CT 5 1 self))
			(7
				(ego setLoop: 2 1 setCel: 0 setCycle: End self)
			)
			(8
				(if (or (== local0 10) (Btst 119))
					(ego normalize: setPri: 53)
					(theGame handsOn:)
					(self dispose:)
				else
					(ego view: 43 setLoop: 2 1 setCel: 0 posn: 95 143)
					(pNec1
						view: 870
						setLoop: 0 1
						setCel: 0
						setPri: 64
						setCycle: Walk
						setMotion: MoveTo 53 145 self
					)
				)
			)
			(9 (ego setCycle: End self))
			(10 (EgoDead 10 600 0 0 912))
		)
	)
)

(instance sSlash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 114 167 self)
			)
			(1 (Face ego 123 168 self))
			(2
				(ego
					view: 38
					setLoop: 1 1
					setCel: 0
					posn: 113 169
					setCycle: End self
				)
			)
			(3
				(sndSwish play:)
				(ego setLoop: 5 1 setCel: 0 x: 115 setCycle: Osc 4 self)
			)
			(4
				(ego setCel: 8 setCycle: Beg self)
			)
			(5
				(ego setLoop: 1 1 setCel: 10 x: 113 setCycle: Beg self)
			)
			(6
				(ego posn: 114 167 normalize:)
				(messager say: 12 6 8 0 self)
			)
			(7
				(if local1
					(messager say: 2 6 8 0 self)
				else
					(self cue:)
				)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sZapTheGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 80 167 self)
			)
			(1
				(ego
					view: 31
					setLoop: 1 1
					setCel: 0
					setCycle: CT 2 1 self
				)
			)
			(2
				(zappedSound play:)
				(aZap init: setCycle: CT 1 1 self)
			)
			(3
				(aZap setCycle: CT 0 -1 self)
			)
			(4
				(zappedSound stop:)
				(aZap dispose:)
				(if (< (= [egoStats 17] (- [egoStats 17] 50)) 0)
					(EgoDead 9 600 0 0 912)
				else
					(ego setCycle: CT 0 -1 self)
				)
			)
			(5
				(ego normalize:)
				(messager say: 11 4 9 0 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOneDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 5)
			)
			(1 (EgoDead 12 600 0 0 912))
		)
	)
)

(instance sDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 5)
			)
			(1 (EgoDead 11 600 0 0 912))
		)
	)
)

(instance sCheckNecs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath local3 local4 self)
			)
			(1
				(ego
					view: 4
					setLoop: (if (< (ego x?) 50) 2 else 0) 1
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(messager say: 3 4 14 0 self)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGoToCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 4)
			)
			(1
				(= global365 870)
				(= monsterHealth 350)
				(pNec2
					view: 870
					setLoop: 1 1
					setCycle: Walk
					setMotion: MoveTo (ego x?) (ego y?)
				)
				(= cycles 24)
			)
			(2
				(Bset 378)
				(curRoom newRoom: 810)
			)
		)
	)
)

(instance sForceGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 108 157 self)
			)
			(1
				(sndMetalGate play:)
				(aGate
					setLoop: 0 1
					setCel: 0
					setMotion: MoveTo -30 103 self
				)
				(= cycles 5)
			)
			(2
				(= global365 870)
				(= monsterHealth 350)
				(sndMetalGate stop:)
				(ego setMotion: MoveTo 107 155 self)
			)
			(3)
			(4
				(Bset 102)
				(Bset 378)
				(curRoom newRoom: 810)
			)
		)
	)
)

(instance sFromSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local0 10)
					(ego setScaler: Scaler 130 45 189 109)
				else
					(ego setScaler: Scaler 120 105 189 156)
				)
				(ego setMotion: PolyPath 121 181 self)
			)
			(1
				(if Night
					(if (== heroType 3) (messager say: 12 6 118))
					(sndLightning play:)
				)
				(if (not (curRoom script?)) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance sFromCastle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 101 126 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTo610 of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setLoop: 6 1 setMotion: MoveTo 227 116 self)
			)
			(1 (curRoom newRoom: 610))
		)
	)
)

(instance sEvent2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gCurrentDay_12 Day)
				(Bset 120)
				(= seconds 5)
			)
			(1
				(messager say: 12 6 24 0 self)
			)
			(2
				(if local1 (messager say: 2 6 29))
				(self cue:)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gCurrentDay_12 Day)
				(= seconds 5)
			)
			(1
				(if local1 (DailyMsg 2 6 41 self 600) else (self cue:))
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent4 of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 121)
				(= gCurrentDay_12 Day)
				(= seconds 5)
			)
			(1
				(if local1 (messager say: 2 6 32))
				(self cue:)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent5 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 5)
			)
			(1
				(if local1 (DailyMsg 2 6 41 self 600) else (self cue:))
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent6 of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 5)
			)
			(1
				(if local1 (DailyMsg 2 6 41 self 600) else (self cue:))
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent7 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 123)
				(= seconds 5)
			)
			(1
				(if local1 (messager say: 2 6 33))
				(self cue:)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent8 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 124)
				(= seconds 5)
			)
			(1
				(messager say: 16 6 35 0 self)
				(self cue:)
			)
			(2 (Face ego 61 174 self))
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent9 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 399)
				(Bset 125)
				(= seconds 5)
			)
			(1
				(messager say: 16 6 37 0 self)
			)
			(2 (Face ego 61 174 self))
			(3
				(if (> [egoStats 17] (ego maxHealth:))
					(ego get: 3)
					(messager say: 16 6 39 1 self)
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

(instance sEvent10 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 101)
				(= seconds 3)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent11 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 30)
			)
			(1
				(ego
					view: 161
					setLoop: 2
					setScaler: Scaler 130 45 189 109
					posn: 111 173
				)
				(ego cel: (ego lastCel:) setCycle: Beg self)
			)
			(2
				(ego setLoop: 1)
				(ego cel: (ego lastCel:) setCycle: Beg self)
			)
			(3
				(ego setLoop: 0)
				(ego cel: (ego lastCel:) setCycle: Beg self)
			)
			(4
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKeeperIncidental of Script
	(properties)
	
	(method (doit)
		(if (ego mover?)
			(cond 
				((< (ego x?) 140) (gateKeeper setCel: 3))
				((< (ego x?) 192) (gateKeeper setCel: 0))
				(else (gateKeeper setCel: 1))
			)
			(= state 0)
			(self cue:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1 (= seconds (Random 2 8)))
			(2
				(gateKeeper
					setLoop: 1 1
					setCel: (if (== (gateKeeper cel?) 3)
						0
					else
						(+ (gateKeeper cel?) 1)
					)
				)
				(= cycles 1)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance aKatrina of Actor
	(properties
		noun 16
		approachX 61
		approachY 174
		x 20
		y 182
		view 602
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 37)
				(if (== (ego has: 5) 1)
					(messager say: 12 6 116)
				else
					(messager say: 12 6 27)
				)
			)
			((OneOf theVerb 36 21) (messager say: 12 6 27))
			(else (super doVerb: theVerb))
		)
	)
)

(instance aGate of Actor
	(properties
		noun 11
		approachX 123
		approachY 168
		x 27
		y 97
		view 600
	)
	
	(method (doVerb theVerb)
		(= projX ((User curEvent?) x?))
		(= projY ((User curEvent?) y?))
		(switch theVerb
			(1
				(cond 
					((Btst 122) (messager say: 11 1 5))
					((and (!= local0 10) Night) (messager say: 11 1 2))
					((!= local0 10) (messager say: 11 1 3))
					(else (super doVerb: theVerb))
				)
			)
			(37
				(if (== (ego has: 5) 1)
					(messager say: 12 6 116)
				else
					(ego trySkill: 10)
					(ego use: 5 1)
					(= local5 1)
					(curRoom setScript: sThrowIt)
				)
			)
			(21
				(ego trySkill: 10)
				(ego use: 6 1)
				(= local5 2)
				(curRoom setScript: sThrowIt)
			)
			(82
				(if local7
					(messager say: 12 6 28)
				else
					(= projX ((User curEvent?) x?))
					(= projY ((User curEvent?) y?))
					(= local5 9)
					(curRoom setScript: (ScriptID 11) 0 self)
				)
			)
			(-82
				(ego trySkill: 22)
				(curRoom setScript: sThrowIt)
			)
			(4
				(curRoom setScript: sZapTheGate)
			)
			(36 (curRoom setScript: sSlash))
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(ego trySkill: 20)
				(curRoom setScript: (ScriptID 13) 0 aGate)
			)
			(-80
				(theGame handsOn:)
				(curRoom setScript: 0)
				(ego trySkill: 20)
				(messager say: 12 6 7)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pNec1 of Actor
	(properties
		noun 3
		x 33
		y 133
		fixPriority 1
		view 872
		scaleSignal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self ignoreActors: 1 setScaler: Scaler 80 72 144 115)
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 1) (Btst 119))
			(messager say: 3 1 0)
			(return 1)
		else
			(messager say: 3 1 117)
			(return 1)
		)
		(return
			(if (and (== theVerb 4) (Btst 119))
				(= local3 53)
				(= local4 136)
				(curRoom setScript: sCheckNecs)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance pNec2 of Actor
	(properties
		noun 3
		x 110
		y 150
		view 872
		loop 1
		scaleSignal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPri: 17
			ignoreActors: 1
			setScaler: Scaler 80 76 153 122
		)
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 1) (Btst 119))
			(messager say: 3 1 0)
			(return 1)
		else
			(messager say: 3 1 117)
			(return 1)
		)
		(return
			(if (and (== theVerb 4) (Btst 119))
				(= local3 252)
				(= local4 163)
				(curRoom setScript: sCheckNecs)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance aZap of Actor
	(properties
		x 79
		y 166
		view 31
		loop 2
		signal $4001
	)
)

(instance gateKeeper of Prop
	(properties
		noun 2
		x 155
		y 108
		priority 141
		fixPriority 1
		view 600
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 59) (ego use: 40))
		(super doVerb: theVerb &rest)
	)
)

(instance fNextRoom of Feature
	(properties
		nsLeft 225
		nsTop 105
		nsRight 233
		nsBottom 117
		sightAngle 180
		x 225
		y 107
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 225 105 233 105 233 117 225 109
						yourself:
					)
					0
					3
					6
					sTo610
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fStop of Feature
	(properties
		nsLeft 225
		nsTop 105
		nsRight 233
		nsBottom 117
		sightAngle 180
		x 225
		y 107
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 85 184 264 184 265 189 63 189 67 184
						yourself:
					)
					2
					4
					5
					sStop
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fCastle of Feature
	(properties
		noun 4
		nsLeft 232
		nsTop 10
		nsRight 265
		nsBottom 54
		sightAngle 180
		x 248
		y 32
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if Night
				(messager say: 4 1 2)
			else
				(messager say: 4 1 3)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fRoad of Feature
	(properties
		noun 5
		nsLeft 229
		nsTop 54
		nsRight 287
		nsBottom 115
		sightAngle 180
		x 258
		y 84
	)
)

(instance fRoad2 of Feature
	(properties
		noun 5
		nsLeft 185
		nsTop 95
		nsRight 236
		nsBottom 110
		sightAngle 180
		x 210
		y 102
	)
)

(instance fTree of Feature
	(properties
		noun 6
		nsLeft 265
		nsTop 1
		nsRight 319
		nsBottom 150
		sightAngle 180
		approachX 262
		approachY 156
		x 1
		y 75
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if Night
				(messager say: 6 1 2)
			else
				(messager say: 6 1 3)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fTowerWindow of Feature
	(properties
		noun 7
		nsLeft 151
		nsTop 92
		nsRight 176
		nsBottom 127
		sightAngle 180
		x 163
		y 109
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if local1
				(messager say: 7 1 3)
			else
				(messager say: 7 1 2)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fParapet of Feature
	(properties
		noun 8
		nsLeft 119
		nsTop 11
		nsRight 192
		nsBottom 37
		sightAngle 180
		x 155
		y 121
		z 97
	)
)

(instance fTower of Feature
	(properties
		noun 9
		nsLeft 124
		nsTop 1
		nsRight 187
		nsBottom 154
		sightAngle 180
		x 155
		y 77
	)
)

(instance fArchWay of Feature
	(properties
		noun 10
		nsLeft 45
		nsTop 31
		nsRight 124
		nsBottom 59
		sightAngle 180
		x 84
		y 100
		z 55
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if [egoStats 11]
				(messager say: 10 1 4)
			else
				(messager say: 10 1 0 0)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance keeperTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 66 0))
	)
	
	(method (sayMessage)
		(switch iconValue
			(48
				(ego addHonor: 50)
				(= local8 1)
			)
			(49
				(if (not local9) (= local9 1) (ego addHonor: 50))
			)
			(86
				(if (not local9) (= local9 1) (ego addHonor: 50))
			)
			(50
				(if (not local9) (= local9 1) (ego addHonor: 50))
			)
			(87
				(if (not local9) (= local9 1) (ego addHonor: 50))
			)
			(51
				(if (not local9) (= local9 1) (ego addHonor: 50))
			)
			(88
				(if (not local9) (= local9 1) (ego addHonor: 50))
			)
			(52
				(if (not local9) (= local9 1) (ego addHonor: 50))
			)
			(53
				(if (not local9) (= local9 1) (ego addHonor: 50))
			)
			(43 (Bset 86))
		)
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super
			showCases:
				43
				(not (Btst 86))
				47
				(if (and (Btst 86) (not (Btst 122)))
					(== local2 1)
				else
					0
				)
				44
				(if (Btst 122) (Btst 86) else 0)
				45
				(if (and (not (Btst 122)) (Btst 86))
					(== local2 3)
				else
					0
				)
				46
				(if (and (not (Btst 122)) (Btst 86))
					(== local2 2)
				else
					0
				)
				48
				(if (== stovichState 1) (not local8) else 0)
				49
				(cond 
					((== stovichState 2))
					((== stovichState 3) local10)
				)
				86
				(if (== stovichState 3) (not local10) else 0)
				50
				(cond 
					((== stovichState 4))
					(local10 (== stovichState 5))
				)
				87
				(if (== stovichState 5) (not local10) else 0)
				51
				(cond 
					((== stovichState 6))
					(local10 (== stovichState 7))
				)
				88
				(if (== stovichState 7) (not local10) else 0)
				52
				(cond 
					((== stovichState 8))
					(local10 (== stovichState 9))
				)
				53
				(if (== stovichState 9) (not local10) else 0)
		)
	)
)

(instance heroTeller of Teller
	(properties)
	
	(method (sayMessage)
		(switch iconValue
			(48
				(ego addHonor: 50)
				(= local8 1)
			)
			(49
				(ego addHonor: 50)
				(= local10 1)
				(= stovichState 3)
			)
			(50
				(ego addHonor: 50)
				(= local10 1)
				(= stovichState 5)
			)
			(51
				(ego addHonor: 50)
				(= local10 1)
				(= stovichState 7)
			)
			(52
				(ego addHonor: 50)
				(= local10 1)
				(= stovichState 9)
			)
		)
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super
			showCases:
				48
				(if (== stovichState 1) (not local8) else 0)
				49
				(if (== stovichState 2) (Btst 85) else 0)
				50
				(if (== stovichState 4) (Btst 85) else 0)
				51
				(if (== stovichState 6) (Btst 85) else 0)
				52
				(if (== stovichState 8) (Btst 85) else 0)
				59
				(if (== local0 2) else (== local0 3))
				65
				(if (== local0 4) else (== local0 5))
				60
				(if (== local0 4) else (== local0 5))
				33
				(Btst 122)
				58
				(Btst 122)
		)
	)
)

(instance gateTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(10
				(ego trySkill: 11)
				(self clean:)
				(curRoom setScript: sClimbTheGate)
			)
			(17
				(ego trySkill: 0)
				(if (== (ego trySkill: 0 325) 1)
					(self clean:)
					(curRoom setScript: sForceGate)
				else
					(super sayMessage: 11 4 13 &rest)
				)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				19
				[egoStats 11]
				10
				[egoStats 11]
				23
				(if (and (== heroType 2) [egoStats 9])
					(ego has: 24)
				else
					0
				)
				18
				(if (and (== heroType 2) [egoStats 9])
					(ego has: 24)
				else
					0
				)
				17
				(if (== heroType 3) else (== heroType 0))
				20
				(if (== heroType 3) else (== heroType 0))
		)
	)
)

(instance katrinaTeller of Teller
	(properties
		title 1
		actionVerb 2
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 68 0))
	)
	
	(method (sayMessage)
		(if (== local6 3)
			(self clean:)
			(curRoom setScript: sKatrinaWalksAway)
		else
			(++ local6)
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super showCases: 66 [egoStats 12])
	)
)

(instance specialEFtr of Feature
	(properties)
	
	(method (init)
		(if (not (curRoom exitList?))
			(curRoom exitList: (List new:))
		)
		((curRoom exitList?) add: self)
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
	
	(method (onMe theObjOrX theY)
		(return
			(if
				(and
					(<= nsLeft theObjOrX)
					(<= theObjOrX nsRight)
					(<= nsTop theY)
					(<= theY nsBottom)
				)
				(if (and approachX approachY)
					(curRoom south: 552)
					(ego
						setMotion: ((ScriptID 17) new:) approachX approachY
					)
				)
				(return 1)
			else
				0
			)
		)
	)
)

(instance zappedSound of Sound
	(properties
		number 968
		loop -1
	)
)

(instance sndNecRoar of Sound
	(properties
		number 872
	)
)

(instance sndSwish of Sound
	(properties
		number 967
	)
)

(instance sndMetalGate of Sound
	(properties
		number 972
	)
)

(instance sndLightning of Sound
	(properties
		number 974
	)
)
