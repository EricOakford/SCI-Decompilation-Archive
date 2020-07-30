;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include game.sh)
(use Main)
(use Intrface)
(use ZZTop)
(use Talker)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use DPath)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm43 0
	someoneDied 1
	slot 2
)

(local
	local0
	saveBits
	local2 =  1
	theRegister
	[slotPts 46] = [0 0 319 0 319 111 298 104 285 107 273 111 250 106 233 99 243 91 232 88 195 83 175 83 194 95 192 103 178 107 140 114 121 115 106 114 85 106 67 97 50 94 43 90 0 101]
	[stagePts 42] = [0 0 319 0 319 111 298 104 279 107 255 98 251 89 232 88 195 83 175 83 194 95 192 103 178 107 140 114 121 115 106 114 85 106 67 97 50 94 43 90 0 99]
	local92
	local93
	scareCount
)
(instance rm43 of Room
	(properties
		picture 43
	)
	
	(method (init)
		(LoadMany VIEW 30 143)
		(Load TEXT 143)
		(if (and (Btst fSlotMachineBroken) (== prevRoomNum 44))
			(Load VIEW 243)
		)
		(super init:)
		(if (!= prevRoomNum 44) (= bandInBar 0) else (Bset fSlotAlienDead))
		(slotPoly type: 2 points: @slotPts size: 23)
		(stagePoly type: 2 points: @stagePts size: 21)
		(if (not (Btst fSlotAlienDead))
			(self addObstacle: slotPoly)
			((ScriptID SLOTGUY 1) init: setScript: (ScriptID SLOTGUY 0))
			((ScriptID SLOTGUY 2) init: stopUpd:)
		else
			(self addObstacle: stagePoly)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 319 189 211 189 181 171 188 161 232 157 261 152 319 151
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 70 170 148 170 148 185 70 185
					yourself:
				)
		)
		(features
			add: slotMachine barTop kzitten fenrisBody
			eachElementDo: #init
		)
		(switch
			(if bandInBar
			else
				(= bandInBar (Random 1 (if (not (Btst fSlotAlienDead)) 2 else 3)))
			)
			(1
				((ScriptID ZZTOP 2) init: setCycle: FROR 8 20 0 2)
				((ScriptID ZZTOP 3) init: setCycle: Forward)
				((ScriptID ZZTOP 4) init: setCycle: Forward)
				(addToPics add: (ScriptID 302 1))
				(music number: 611)
			)
			(2
				((ScriptID BLUESBROS 1)
					init:
					setScript: (Clone (ScriptID BLUESBROS 0))
				)
				((ScriptID BLUESBROS 2)
					init:
					setScript: (Clone (ScriptID BLUESBROS 0)) 0 3
				)
				(music number: 612 send: 4 78 0 send: 5 78 0)
			)
			(3
				((ScriptID MADONNA 1) init: setScript: (ScriptID MADONNA 0))
				((ScriptID MADONNA 2) init: setCycle: Forward hide:)
				(music number: 613 send: 6 78 0)
			)
		)
		(slugGuy init: stopUpd:)
		(triGirl init: stopUpd:)
		(fenris init: setScript: fenrisScript)
		(bartender init: setScript: bartendScript)
		(chp_dail init: setScript: beerJumping)
		(if (<= (theGame detailLevel:) 2)
			(slugGuy setScript: slugScript)
			(triGirl setScript: triScript)
		)
		(kzittenArm init: setPri: 12 stopUpd:)
		(kzittenHead init: setPri: 12 stopUpd:)
		(slugEyes init: hide:)
		(bartenderHead init:)
		(beerMug init: setCycle: Walk hide:)
		(C_DbeerMug init: setCycle: Walk)
		(addToPics eachElementDo: #init doit:)
		(slot init:)
		(if (or (not (Btst fSlotAlienDead)) (Btst fSlotsDeath))
			(Load SOUND 312)
			(sweeper init: stopUpd:)
		)
		(if (== prevRoomNum 44)
			(NormalEgo 0 1 61)
			(ego
				init:
				posn: 256 105
				view: 77
				setLoop: 0
				cel: 5
				normal: 0
				illegalBits: cWHITE
			)
			(cond 
				((Btst fSlotMachineBroken)
					(curRoom setScript: playSlots 0 2)
				)
				((Btst fWidgetOnSlots)
					(curRoom setScript: playSlots 0 3)
				)
				((Btst fSlotsDeath)
					(theEgoHead hide:)
					(curRoom setScript: someoneDied 0 ego)
				)
				(else
					(curRoom setScript: playSlots 0 1)
				)
			)
			(if (< 3 4) (music loop: -1 flags: 1 play:))
		else
			(if (Btst fSlotMachineBroken)
				(slot view: 243 posn: 282 81 loop: 7 cel: 2 stopUpd:)
			)
			(music loop: -1 flags: mNOPAUSE play:)
			(self setScript: walkIn)
		)
		(if (== (music number?) 612)
			(music send: 4 78 1 send: 5 78 1)
		)
		(if (== (music number?) 613) (music send: 6 78 1))
		(if (Btst fPlayedSlots)
			(widget init: setCycle: Forward)
		else
			(widget init: hide:)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script 0)
			((not (= temp0 (ego onControl: origin))) 0)
			((& temp0 $0020) (self setScript: walkOut))
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Printf 43 0
					(switch bandInBar
						(1
							{A bearded band is cranking out some of the more popular tunes in the quadrant}
						)
						(2
							{These are a couple of nongalactic-looking humanoids cranking out some unfamiliar-sounding tunes. They seem interested solely in the music they are performing.}
						)
						(3
							{On stage, a strangely attired woman performs her act (if that's what you want to call it). You haven't had a girlfriend for a long time (more like forever), but even that's not enough to make here attractive.}
						)
					)
				)
			)
			(verbSmell
				(Print 43 1)
			)
			(verbTaste
				(if (<= alesDrunk 1)
					(Print 43 2)
				else
					(Print 43 3)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(if (!= n 44) (= insertedBuckazoids 0))
		(if (not (Btst fSlotAlienDead)) ((ScriptID SLOTGUY 1) dispose:))
		(super newRoom: n)
	)
)

(instance walkIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					init:
					setLoop: 6
					setPri: 14
					posn: -10 170
					setMotion: MoveTo 24 193 self
				)
			)
			(1
				(ego
					setPri: -1
					setLoop: -1
					setStep: 4 2
					setMotion: MoveTo 71 166 self
				)
			)
			(2
				(ego illegalBits: cWHITE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance walkOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: PolyPath 24 193 self)
			)
			(1
				(ego setPri: 14 setLoop: 5 setMotion: MoveTo -10 170 self)
			)
			(2
				(ego setPri: -1 setLoop: -1)
				(curRoom newRoom: 41)
			)
		)
	)
)

(instance egoStaggersOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 43 4)
				(= cycles 18)
			)
			(1 (ego setCycle: BegLoop self))
			(2
				(Print 43 5)
				(beerMug show:)
				(ego
					posn: 238 134
					setPri: 11
					view: 30
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(ego posn: 229 157 view: 32 setLoop: 0 cel: 0 setPri: 10)
				(= cycles 12)
			)
			(4
				(ego setLoop: 2 cel: 2)
				(= cycles 6)
			)
			(5 (ego cel: 0) (= cycles 6))
			(6 (ego cel: 5) (= cycles 6))
			(7
				(ego setLoop: 1 cel: 4)
				(= cycles 6)
			)
			(8
				(ego
					setCycle: Forward
					cycleSpeed: 5
					moveSpeed: 5
					setMotion: MoveTo 132 164 self
				)
			)
			(9
				(kzittenHead setScript: scareEgo)
				(= cycles 30)
			)
			(10
				(ego setMotion: MoveTo 41 187 self)
			)
			(11
				(ego
					moveSpeed: 8
					cycleSpeed: 8
					setMotion: MoveTo 4 180 self
				)
			)
			(12
				(ego setPri: -1 setLoop: -1)
				(curRoom newRoom: 41)
			)
		)
	)
)

(instance scareEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (= register (User canControl:)) (HandsOff))
				(kzittenHead setCycle: EndLoop self)
			)
			(1
				(kzittenHead stopUpd:)
				(kzittenArm setCycle: EndLoop self)
			)
			(2
				(kzittenArm stopUpd:)
				(= cycles 10)
			)
			(3
				(kzittenHead setCycle: BegLoop)
				(kzittenArm setCycle: BegLoop self)
			)
			(4
				(kzittenHead stopUpd:)
				(kzittenArm stopUpd:)
				(if register (HandsOn))
				(self dispose:)
			)
		)
	)
)

(instance slugScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(slugGuy stopUpd:)
				(= cycles (Random 12 60))
			)
			(1
				(if (Random 0 2)
					(= state -1)
					(slugGuy setLoop: 2 forceUpd:)
					(slugEyes show: setLoop: (Random 3 6) cel: 1)
					(= cycles (Random 15 60))
				else
					(= cycles 3)
				)
			)
			(2
				(slugEyes hide:)
				(slugGuy setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(3
				(slugGuy setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(4
				(= state -1)
				(slugGuy setLoop: 0 cel: 8 setCycle: BegLoop self)
			)
		)
	)
)

(instance triScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(triGirl stopUpd:)
				(= cycles (Random 30 180))
			)
			(1
				(if (Random 0 1)
					(= state -1)
					(triGirl
						setLoop: 3
						cycleSpeed: 8
						setCycle: Oscillate (Random 1 3) self
					)
				else
					(= cycles 3)
				)
			)
			(2
				(triGirl
					setLoop: 0
					cycleSpeed: 8
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3 (= cycles (Random 30 90)))
			(4
				(= state -1)
				(triGirl setCycle: BegLoop self)
			)
		)
	)
)

(instance fenrisScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fenris stopUpd:)
				(= cycles (Random 9 90))
			)
			(1
				(if (Random 0 1)
					(= state -1)
					(fenris setLoop: 1 cel: 0 setCycle: Forward)
					(= cycles (Random 15 60))
				else
					(= cycles 3)
				)
			)
			(2
				(fenris setLoop: 0 cel: 2 setCycle: BegLoop self)
			)
			(3
				(fenris
					setLoop: 2
					cel: 0
					setCycle: ForwardCounter (Random 1 4) self
				)
			)
			(4
				(= state -1)
				(fenris setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
		)
	)
)

(instance bartendScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not local92)
				(or
					(>= (bartendScript register?) 4)
					(>= (bartendScript register?) 8)
				)
				(not (OneOf state 3 2 8))
			)
			(= local92 1)
			(bartenderT
				init: bartenderBust bartenderEyes bartenderMouth
			)
			(bartenderT say: 143 (Random 0 2) 0 1 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						register
						(not (>= (bartendScript register?) 4))
						(not (>= (bartendScript register?) 8))
					)
					(= state 4)
					(= theRegister register)
					(= cycles 9)
				else
					(= cycles (Random 15 60))
				)
			)
			(1
				(if (not (>= (bartendScript register?) 4))
					(= state -1)
				)
				(if
					(and
						(not (>= (bartendScript register?) 8))
						(>= (bartendScript register?) 4)
					)
					(= state 2)
				)
				(bartender setLoop: 1)
				(bartenderHead show: posn: 284 135 cel: 0)
				(if (>= (bartendScript register?) 4)
					(bartenderHead setLoop: 3 setCycle: EndLoop self)
				else
					(bartenderHead setLoop: 2 setCycle: EndLoop self)
				)
			)
			(2
				(= local92 0)
				(bartenderT
					init: bartenderBust bartenderEyes bartenderMouth 143 3 0 1
				)
				(ego put: 17 43)
				(= buckazoids (+ buckazoids 5))
				(self register: (- (bartendScript register?) 8))
				(self register: (+ (bartendScript register?) 2))
				(= state 4)
				(= cycles 5)
			)
			(3
				(bartenderT
					init:
						bartenderBust bartenderEyes bartenderMouth
						{"So, what's your beef, junior?"}
						0 0 0
						self
				)
			)
			(4
				(= local92 0)
				(if
					(or
						(and
							(not alesDrunk)
							(or (and modelessDialog (modelessDialog dispose:)) 1)
							(Print 43 6 #at 10 10 #button { Yes_} 1 #button { No_} 0)
						)
						(and
							(< 0 alesDrunk)
							(< alesDrunk 5)
							(or (and modelessDialog (modelessDialog dispose:)) 1)
							(Print 43 7 #at 10 10 #button { Yes_} 1 #button { No_} 0)
						)
						(and
							(== alesDrunk 5)
							(or (and modelessDialog (modelessDialog dispose:)) 1)
							(Print 43 8
								#at 10 10
								#button { Gimme anudder un, ya jerr-erk!_} 1
								#button { Ok._} 0
							)
						)
					)
					(cond 
						((< buckazoids 2)
							(bartenderT say: 143 5 0 1)
							(= sittingAtBar 0)
							(if (not (someoneDied client?))
								(HandsOn)
								(User canControl: FALSE)
							)
							(= state -1)
						)
						((== buckazoids 2)
							(= buckazoids 0)
							(ego put: iBuckazoid)
							(self register: (+ (bartendScript register?) 2))
							(bartenderT say: {"Here ya go."} 0 0 1)
						)
						(else
							(= buckazoids (- buckazoids 2))
							(self register: (+ (bartendScript register?) 2))
							(bartenderT say: {"Here ya go."} 0 0 1)
						)
					)
					(= cycles 5)
				else
					(= sittingAtBar 0)
					(if (not (someoneDied client?))
						(HandsOn)
						(User canControl: FALSE)
					)
					(bartenderT
						init:
							bartenderBust bartenderEyes bartenderMouth
							{"So stop botherin' me already."}
							0 0 1
							self
					)
					(= state -1)
				)
				(self register: (- (bartendScript register?) 4))
			)
			(5
				(= theRegister register)
				(bartenderHead hide:)
				(bartender setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(6
				(bartender setLoop: 4 cel: 0)
				(bartenderHead
					show:
					posn: 300 143
					setLoop: 5
					setCycle: CycleTo 11 1 self
				)
			)
			(7
				(cond 
					((>= theRegister 4)
						(self
							register: (- (bartendScript register?) 1)
							setScript: drinkDownBar self 1
						)
					)
					((>= theRegister 2)
						(self
							register: (- (bartendScript register?) 2)
							setScript: drinkDownBar self
						)
					)
					((>= theRegister 1)
						(self
							register: (- (bartendScript register?) 1)
							setScript: drinkDownBar self 1
						)
					)
				)
				(= theRegister 0)
				(bartenderHead setCycle: EndLoop self)
			)
			(8
				(= state -1)
				(bartenderHead posn: 248 135 hide:)
				(bartender setLoop: 0 cel: 3 setCycle: BegLoop)
			)
		)
	)
)

(instance drinkDownBar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(C_DbeerMug
						show:
						posn: 289 141
						setMotion: DPath 255 140 242 144 238 151 240 165 237 167 209 187 self
					)
				else
					(beerMug
						show:
						posn: 289 141
						setMotion: MoveTo 252 136 self
					)
				)
			)
			(1
				(if register
					(= register 0)
					(C_DbeerMug cel: 0)
					(= local2 1)
					(beerJumping cue:)
				else
					(beerMug cel: 0)
					(moveEgoToBar cue:)
				)
				(self dispose:)
			)
		)
	)
)

(instance beerJumping of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chp_dail cycleSpeed: 8)
				(= state (- (Random 1 3) 1))
				(= cycles (Random 12 90))
			)
			(1
				(= state -1)
				(if local2 (C_DbeerMug show:))
				(chp_dail
					setLoop: 2
					cel: 0
					cycleSpeed: 4
					setCycle: Oscillate 1 self
				)
			)
			(2
				(= state -1)
				(if local2 (C_DbeerMug hide:))
				(chp_dail setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(if local2 (C_DbeerMug hide:))
				(chp_dail setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(4
				(chp_dail setLoop: 3 cel: 0 setCycle: Forward)
				(= seconds (Random 2 5))
			)
			(5
				(chp_dail setLoop: 2 cel: 0 setCycle: 0)
				(bartendScript
					register: (+ (bartendScript register?) 1)
				)
			)
			(6
				(= state -1)
				(chp_dail setLoop: 2 cel: 0)
				(= cycles 3)
			)
		)
	)
)

(instance moveEgoToBar of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(directionHandler addToFront: self)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (OneOf register 0 6))
					(self changeState: 4)
				else
					(= cycles 3)
				)
			)
			(1
				(ego setMotion: MoveTo 240 158 self)
			)
			(2
				(ego
					view: 30
					posn: 238 134
					setPri: 11
					loop: 0
					cel: 9
					setCycle: BegLoop self
				)
			)
			(3 (= cycles 1))
			(4
				(= local0 1)
				(kzittenHead approachVerbs:)
				(kzittenArm approachVerbs:)
				(kzitten approachVerbs:)
				(triGirl approachVerbs:)
				(fenris approachVerbs:)
				(fenrisBody approachVerbs:)
				(if (not local93)
					(if (not sittingAtBar)
						(if (>= register 5)
							(= register 0)
							(Print 43 10)
							(bartendScript
								register: (+ (bartendScript register?) 8)
							)
						else
							(if (not alesDrunk) (Print 43 11) else (Print 43 12))
							(bartendScript
								register: (+ (bartendScript register?) 4)
							)
						)
						(= sittingAtBar 1)
					)
				else
					(HandsOn)
				)
				(= local93 0)
				(= register 0)
				(User canControl: FALSE)
			)
			(5
				(if (< (++ alesDrunk) 6)
					(ego
						view: 90
						setLoop: 0
						cel: 0
						cycleSpeed: 8
						setCycle: CycleTo 1 1 self
					)
				else
					(beerMug hide:)
					(ego
						view: 73
						setLoop: 0
						cel: 0
						cycleSpeed: 8
						setCycle: EndLoop self
					)
				)
			)
			(6
				(beerMug hide:)
				(if (>= alesDrunk 6)
					(curRoom setScript: egoStaggersOut)
					(self dispose:)
				else
					(ego setCycle: CycleTo 8 1 self)
				)
			)
			(7
				(beerMug
					show:
					posn: 264 136
					setMotion: MoveTo 291 136 self
				)
				(= sittingAtBar FALSE)
				(ego setCycle: EndLoop)
			)
			(8
				(switch alesDrunk
					(1
						(Print 43 13)
					)
					(2
						(Print 43 14)
					)
					(3
						(Printf 43 15
							(switch deltaurSector
								(1 {AA})
								(2 {BB})
								(3 {CC})
								(4 {DD})
								(5 {EE})
								(6 {FF})
								(7 {GG})
								(8 {HH})
								(9 {II})
								(10 {AD})
								(11 {BF})
								(12 {BG})
								(13 {CH})
								(14 {CI})
								(15 {DG})
								(16 {EG})
								(17 {HA})
								(18 {HD})
								(19 {IB})
								(20 {IC})
							)
						)
						(SolvePuzzle 5 f43LearnDeltaurSector)
						(Print 43 16)
						(Print 43 17)
						(Print 43 18)
					)
					(4
						(Print 43 19)
					)
					(5
						(Print 43 20)
					)
				)
				(= cycles 3)
			)
			(9
				(beerMug hide:)
				(HandsOn)
				(User canControl: 0)
				0
			)
			(10
				(HandsOff)
				(ego view: 30 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(11
				(ego posn: 240 158 setPri: -1)
				(= local0 0)
				(kzittenHead approachVerbs: verbDo verbTalk)
				(kzittenArm approachVerbs: verbDo verbTalk)
				(kzitten approachVerbs: verbDo verbTalk)
				(triGirl approachVerbs: verbTalk verbDo verbSmell)
				(fenris approachVerbs: verbTalk verbDo verbSmell)
				(fenrisBody approachVerbs: verbTalk verbDo verbSmell)
				(if (not (if (> 6 alesDrunk) (> alesDrunk 4)))
					(NormalEgo 0 1 61)
					(ego illegalBits: cWHITE loop: 4)
				else
					(Print 43 21)
					(NormalEgo 0 1 61)
					(ego illegalBits: cWHITE)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(or (== state 4) (== state 9))
				(== (theIconBar curIcon?) (theIconBar at: ICON_WALK))
				(or
					(& (event type?) direction)
					(== (event message?) dirN)
				)
			)
			(cond 
				(sittingAtBar
					(Print 43 9)
				)
				((== state 4)
					(self changeState: 10)
				)
				(else
					(self cue:)
				)
			)
			(event claimed: TRUE)
		else
			(super handleEvent: event)
		)
	)
)

(instance someoneDied of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== register ego)
					(HandsOff)
					(= cycles 4)
				else
					(self cue:)
				)
			)
			(1
				(if (== register ego)
					(= cycles 2)
				else
					((ScriptID SLOTGUY 2) hide: forceUpd:)
					((ScriptID SLOTGUY 1)
						view: 543
						setLoop: 0
						cel: 0
						ignoreActors: TRUE
						posn: 263 104
						setCycle: EndLoop self
					)
				)
			)
			(2
				(if (== register ego)
					(= saveBits (Graph GSaveBits 20 62 89 255 1))
					(Graph GDrawLine 21 63 78 254 myTextColor7 1 0 0)
					(Graph GReAnimate 20 62 89 255)
				else
					(= saveBits (Graph GSaveBits 20 62 89 250 1))
					(Graph GDrawLine 21 63 88 249 myTextColor7 1 0 0)
					(Graph GReAnimate 20 62 89 250)
				)
				(soundFx number: 312 loop: 1 play:)
				(if (== register ego)
					(ego view: 78 setLoop: 0 cel: 2)
				else
					((ScriptID SLOTGUY 1)
						setLoop: 1
						cel: 0
						posn: 263 104
						setCycle: Forward
					)
				)
				(register setCycle: BegLoop self)
				(= cycles 10)
			)
			(3
				(Graph GRestoreBits saveBits)
				(if (== register ego)
					(Graph GReAnimate 20 62 89 255)
				else
					(Graph GReAnimate 20 62 89 250)
				)
				(= saveBits 0)
			)
			(4
				(if (== register ego)
					(ego
						view: 78
						setLoop: 1
						cel: 0
						ignoreActors: TRUE
						setCycle: EndLoop self
					)
				else
					((curRoom obstacles?) delete: slotPoly add: stagePoly)
					(Bset fSlotAlienDead)
					((ScriptID SLOTGUY 1) setLoop: 2 cel: 0 setCycle: EndLoop self)
				)
			)
			(5
				(globalSound number: 615 loop: -1 play:)
				(sweeper
					setMotion: PolyPath (- (register x?) 10) (- (register y?) 1) self
				)
			)
			(6
				(sweeper
					setLoop: 2
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(7
				(register hide:)
				(sweeper setLoop: 3 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(8
				(globalSound number: 616 loop: -1 play:)
				(sweeper setLoop: 3 cel: 2 setCycle: CycleTo 5 1 self)
			)
			(9
				(globalSound stop:)
				(sweeper setLoop: 3 cel: 6 setCycle: CycleTo 10 1 self)
			)
			(10
				(globalSound number: 616 loop: -1 play:)
				(sweeper setLoop: 3 cel: 11 setCycle: EndLoop self)
			)
			(11
				(globalSound stop:)
				(= cycles 3)
			)
			(12
				(sweeper setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(13
				(globalSound number: 615 loop: -1 play:)
				(sweeper
					setLoop: 1
					cycleSpeed: 4
					setCycle: Forward
					setMotion: PolyPath 325 115 self
				)
			)
			(14
				(if (== register ego)
					(curRoom newRoom: 42)
				else
					(sweeper dispose:)
					((ScriptID SLOTGUY 1) dispose:)
					(if (not sittingAtBar)
						(HandsOn)
						(if local0
							(User canControl: FALSE)
						)
					)
				)
				(UnLoad VIEW 444)
				(UnLoad VIEW 443)
				(globalSound stop:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance playSlots of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (>= 3 register) (>= register 1))
					(self changeState: 3)
				else
					(if (== register 4) (= state 3))
					(if (Btst fSlotAlienDead)
						(ego setMotion: PolyPath 256 105 self)
					else
						(Print 43 22)
						(HandsOn)
						(self dispose:)
					)
				)
			)
			(1
				(if (>= register 3) (widget show: setCycle: Forward))
				(ego view: 77 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(= register 0)
				(curRoom newRoom: 44 IRISOUT)
			)
			(3
				(if (!= register 4)
					(ego view: 77 setLoop: 0 cel: 5 setCycle: BegLoop self)
				)
				(switch register
					(4 (self changeState: 4))
					(3 (= state 3))
					(2 (= state 4))
					(else  (= state 4))
				)
			)
			(4
				(= state 0)
				(Bset fWidgetOnSlots)
				(Bset fPlayedSlots)
				(SolvePuzzle 5 f43PlaySlots)
				(ego
					view: 79
					setLoop: 0
					cel: 0
					put: 15
					setCycle: EndLoop self
				)
			)
			(5
				(if (Btst fPlayedSlots)
					(Bclr fPlayedSlots)
					(widget hide:)
					(ego
						view: 79
						setLoop: 0
						cel: 6
						get: 15
						setCycle: BegLoop self
					)
				else
					(= cycles 3)
				)
			)
			(6
				(if (== register 2)
					(client setScript: breakSlots)
				else
					(NormalEgo 0 1 61)
					(= register 0)
					(ego illegalBits: cWHITE)
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)

(instance breakSlots of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 58
					cel: 0
					loop: 4
					cycleSpeed: 4
					setCycle: EndLoop self
				)
				(soundFx number: 411 loop: 1 play:)
				(slot
					view: 243
					posn: 282 81
					loop: 0
					cel: 0
					cycleSpeed: 9
					setCycle: EndLoop
				)
			)
			(1
				(NormalEgo 0 1 61)
				(= seconds 2)
			)
			(2
				(slot stopUpd:)
				(Print 43 23)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance widget of Prop
	(properties
		x 268
		y 90
		description {widget}
		lookStr {It's the widget you got from the Star Generator's pedestal. Don't you remember?}
		view 143
		loop 3
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbTalk
				(Print 43 24)
			)
			(verbSmell
				(Print 43 25)
			)
			(verbTaste
				(Print 43 26)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance slugEyes of View
	(properties
		x 305
		y 110
		view 440
		cel 3
		priority 11
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(slugGuy doVerb: theVerb &rest)
	)
)

(instance kzittenArm of Prop
	(properties
		x 119
		y 143
		description {kzitten's arm}
		approachX 148
		approachY 185
		view 436
		loop 3
		signal ignrAct
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(kzittenHead doVerb: theVerb &rest)
	)
)

(instance kzittenHead of Prop
	(properties
		x 108
		y 151
		description {kzitten}
		approachX 148
		approachY 185
		lookStr {This character kinda reminds you of a cute, fluffy little kitten you had when you were a kid - except that he weighs about 400 kilos and has foot-long, razor-sharp claws and a bad attitude.}
		view 436
		loop 1
		signal ignrAct
		cycleSpeed 4
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo verbTalk)
		(kzittenArm approachVerbs: verbDo verbTalk)
		(kzitten approachVerbs: verbDo verbTalk)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(self setScript: scareEgo self)
			)
			(verbTalk
				(if local0
					(Print 43 27)
				else
					(Print 43 28 #at 170 101)
					(Print 43 29 #at 38 68)
					(Print 43 30 #at 177 141)
					(Print 43 31 #at 48 108)
				)
			)
			(verbTaste
				(Print 43 32)
			)
			(verbSmell
				(Print 43 33)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 43 34)
					)
					(iCartridge
						(Print 43 35)
					)
					(iWidget
						(Print 43 36)
					)
					(iGadget
						(Print 43 37)
					)
					(iKnife
						(Print 43 38)
					)
					(iDehydratedWater
						(Print 43 39)
					)
					(iJetpack
						(Print 43 40)
					)
					(iBarCoupon
						(Print 43 41)
					)
					(iDroidsBUsCoupon
						(Print 43 41)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(switch (++ scareCount)
			(1
				(Print 43 42)
			)
			(2
				(Print 43 43)
			)
			(else
				(Print 43 44)
			)
		)
	)
)

(instance slugGuy of Prop
	(properties
		x 305
		y 110
		description {slug guy}
		lookStr {This guy appears to have blown in from Santa Cruz.}
		view 440
		priority 11
		signal $4010
		cycleSpeed 4
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 43 45)
			)
			(verbTaste
				(Print 43 46)
			)
			(verbTalk
				(Print 43 47)
			)
			(verbSmell
				(Print 43 48)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 43 49)
					)
					(iCartridge
						(Print 43 50)
					)
					(iWidget
						(Print 43 51)
					)
					(iGadget
						(Print 43 52)
					)
					(iKnife
						(Print 43 53)
					)
					(iDehydratedWater
						(Print 43 54)
					)
					(iJetpack
						(Print 43 55)
					)
					(iBarCoupon
						(Print 43 56)
					)
					(iDroidsBUsCoupon
						(Print 43 56)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance triGirl of Prop
	(properties
		x 268
		y 119
		description {trigirl}
		approachX 223
		approachY 153
		lookStr {You must be getting dizzy - you're seeing triple.}
		view 439
		cel 7
		priority 11
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
		detailLevel 3
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbTalk verbDo verbSmell)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 43 57)
			)
			(verbTaste
				(Print 43 58)
			)
			(verbTalk
				(Print 43 59 #at 128 76)
				(Print 43 60 #at 224 130)
				(Print 43 61)
			)
			(verbSmell
				(Print 43 62)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 43 63)
					)
					(iCartridge
						(Print 43 64)
					)
					(iWidget
						(Print 43 65)
					)
					(iGadget
						(Print 43 66)
					)
					(iKnife
						(Print 43 67)
					)
					(iDehydratedWater
						(Print 43 68)
					)
					(iJetpack
						(Print 43 69)
					)
					(iBarCoupon
						(Print 43 70)
					)
					(iDroidsBUsCoupon
						(Print 43 70)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fenris of Prop
	(properties
		x 215
		y 172
		z 26
		description {fenris}
		approachX 205
		approachY 155
		lookStr {This character looks like an economy-size version of your Uncle Buck's toupee.}
		view 438
		cel 2
		priority 11
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbTalk verbDo verbSmell)
		(fenrisBody approachVerbs: verbTalk verbDo verbSmell)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 43 71)
			)
			(verbTaste
				(Print 43 58)
			)
			(verbTalk
				(Print 43 72 #at 175 68)
				(Print 43 73 #at 224 149)
				(Print 43 74)
			)
			(verbSmell
				(Print 43 75)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 43 76)
					)
					(iCartridge
						(Print 43 77)
					)
					(iWidget
						(Print 43 78)
					)
					(iGadget
						(Print 43 79)
					)
					(iKnife
						(Print 43 80)
					)
					(iDehydratedWater
						(Print 43 81)
					)
					(iJetpack
						(Print 43 82)
					)
					(iBarCoupon
						(Print 43 83)
					)
					(iDroidsBUsCoupon
						(Print 43 83)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bartender of Prop
	(properties
		x 296
		y 169
		description {bartender}
		approachX 240
		approachY 158
		lookStr {A fine example of his species - whatever THAT is.}
		view 442
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo verbTalk)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 43 84)
			)
			(verbTalk
				(cond 
					((not local0) (curRoom setScript: moveEgoToBar))
					((User input?) (curRoom setScript: moveEgoToBar 0 1))
				)
			)
			(verbUse
				(switch theItem
					(iBarCoupon
						(if (not local0)
							(curRoom setScript: moveEgoToBar 0 6)
						else
							(curRoom setScript: moveEgoToBar 0 5)
						)
					)
					(iCartridge
						(Print 43 85)
					)
					(iWidget
						(Print 43 86)
					)
					(iGadget
						(Print 43 87)
					)
					(iKnife
						(Print 43 88)
					)
					(iDehydratedWater
						(Print 43 89)
					)
					(iJetpack
						(Print 43 90)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(verbTaste
				(Print 43 91)
			)
			(verbSmell
				(Print 43 92)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance bartenderHead of Prop
	(properties
		x 284
		y 135
		approachX 240
		approachY 158
		view 442
		loop 2
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo verbTalk)
	)
	
	(method (doVerb theVerb)
		(bartender doVerb: theVerb &rest)
	)
)

(instance chp_dail of Prop
	(properties
		x 201
		y 185
		description {chip and dale}
		lookStr {Cute little purple guys, eh! They sure can put away the brew.}
		view 437
		loop 2
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
		detailLevel 2
		name "chp&dail"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 43 93)
			)
			(verbTaste
				(Print 43 58)
			)
			(verbTalk
				(Print 43 94)
			)
			(verbSmell
				(Print 43 95)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 43 96)
					)
					(iCartridge
						(Print 43 97)
					)
					(iWidget
						(Print 43 98)
					)
					(iGadget
						(Print 43 99)
					)
					(iKnife
						(Print 43 100)
					)
					(iDehydratedWater
						(Print 43 101)
					)
					(iJetpack
						(Print 43 102)
					)
					(iBarCoupon
						(Print 43 103)
					)
					(iDroidsBUsCoupon
						(Print 43 103)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance slot of Prop
	(properties
		x 279
		y 70
		view 143
	)
	
	(method (doVerb theVerb)
		(slotMachine doVerb: theVerb &rest)
	)
)

(instance sweeper of Actor
	(properties
		x 325
		y 115
		description {sweeper}
		lookStr {The sweeper is a small motorized device which scans the floor for debris and removes it within moments of detection. There is nothing special about it, otherwise.}
		view 444
		signal ignrAct
		cycleSpeed 4
		illegalBits $0000
		moveSpeed 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbTaste
				(Print 43 104)
			)
			(verbSmell
				(Print 43 105)
			)
		)
	)
)

(instance beerMug of Actor
	(properties
		x 252
		y 136
		description {beer mug}
		lookStr {The beer mug is in the shape of a rocket.}
		yStep 5
		view 143
		loop 4
		priority 11
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 1
		xStep 5
		moveSpeed 1
	)
)

(instance C_DbeerMug of Actor
	(properties
		x 209
		y 187
		description {beer mug}
		lookStr {The beer mug is just a normal beer mug.}
		yStep 5
		view 143
		loop 2
		priority 15
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 1
		xStep 5
		moveSpeed 1
		name "C&DbeerMug"
	)
)

(instance fenrisBody of Feature
	(properties
		description {fenris}
		sightAngle 45
		onMeCheck $0080
		approachX 205
		approachY 155
		lookStr {not used}
	)
	
	(method (doVerb theVerb)
		(fenris doVerb: theVerb &rest)
	)
)

(instance kzitten of Feature
	(properties
		description {kzitten}
		sightAngle 45
		onMeCheck $0040
		approachX 148
		approachY 185
		lookStr {not used}
	)
	
	(method (doVerb theVerb)
		(kzittenHead doVerb: theVerb &rest)
	)
)

(instance lightCannon of Feature
	(properties
		x 41
		y 10
		description {light cannon}
		onMeCheck $0200
		lookStr {This appears to be a 6 megawatt Kurtzman Kannon of the type used to signal the end of particularly bad acts on the old LaserGong show.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 43 106)
			)
			(verbSmell
				(Print 43 107)
			)
			(verbTaste
				(Print 43 108)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance barTop of Feature
	(properties
		description {barTop}
		onMeCheck $0008
		approachX 240
		approachY 158
		lookStr {This is the bar top.}
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(= x ((User curEvent?) x?))
				(= y ((User curEvent?) y?))
				(super doVerb: theVerb theItem &rest)
			)
			(verbDo
				(if (not local0)
					(= local93 1)
					(curRoom setScript: moveEgoToBar)
				else
					(= local93 1)
					(curRoom setScript: moveEgoToBar 0 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance slotMachine of Feature
	(properties
		x 282
		y 77
		nsTop 50
		nsLeft 259
		nsBottom 105
		nsRight 305
		description {slot machine}
		sightAngle 45
		onMeCheck FARCHECK
		lookStr {It appears to be an electronic slot machine.}
	)
	
	(method (doVerb theVerb theItem)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(verbDo
				(cond 
					((Btst fSlotMachineBroken) (Print 43 109))
					((not (curRoom script?)) (curRoom setScript: playSlots))
					(else (super doVerb: theVerb theItem))
				)
			)
			(verbUse
				(if (Btst fSlotMachineBroken)
					(Print 43 109)
				else
					(switch theItem
						(iWidget
							(if (not (curRoom script?))
								(curRoom setScript: playSlots 0 4)
							else
								(super doVerb: theVerb theItem)
							)
						)
						(iBuckazoid
							(if (not (curRoom script?))
								(curRoom setScript: playSlots)
							else
								(super doVerb: theVerb theItem)
							)
						)
						(else 
							(super doVerb: theVerb theItem)
						)
					)
				)
			)
			(verbTaste
				(Print 43 110)
			)
			(verbSmell
				(Print 43 111)
			)
			(verbLook
				(if (Btst fSlotMachineBroken)
					(Print 43 109)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance slotPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance stagePoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance bartenderT of Talker
	(properties
		x 10
		y 10
		nsTop 6
		nsLeft 205
		view 511
	)
)

(instance bartenderBust of View
	(properties
		view 511
		cel 1
	)
)

(instance bartenderEyes of Prop
	(properties
		nsTop 13
		nsLeft 37
		view 511
		loop 1
		cycleSpeed 30
	)
)

(instance bartenderMouth of Prop
	(properties
		nsTop 23
		nsLeft 6
		view 511
		loop 2
		cycleSpeed 12
	)
)
