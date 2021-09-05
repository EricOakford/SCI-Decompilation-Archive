;;; Sierra Script 1.0 - (do not remove this comment)
(script# 550)
(include game.sh)
(use Main)
(use GloryControls)
(use Print)
(use IconBar)
(use LoadMany)
(use Timer)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	combatRm 0
	WarriorObj 1
	combatSpell 2
	monsterHPStat 3
)

(local
	local0
	theGameTime
)
(procedure (localproc_0344 &tmp temp0 temp1 temp2)
	(= temp0 (FirstNode (cast elements?)))
	(while temp0
		(= temp1 (NextNode temp0))
		(if (not (IsObject (= temp2 (NodeValue temp0))))
			(return)
		)
		(if (temp2 respondsTo: #setCycle)
			(temp2 setCycle: 0)
			(if (temp2 respondsTo: #setMotion) (temp2 setMotion: 0))
		)
		(= temp0 temp1)
	)
)

(procedure (localproc_03a1)
	(HandsOff)
	(if (<= [egoStats 16] 0) (= battleResult 0))
	(localproc_0344)
	(if (spellTimer seconds?) (spellTimer dispose:))
	(if (stamTimer seconds?) (stamTimer dispose:))
	(if (duckTimer seconds?) (duckTimer dispose:))
	(SetCursor -2)
	(combatControls
		state: (& (combatControls state?) $ffdf)
	)
	(theIconBar enable:)
	(if (== prevRoomNum 549)
		(curRoom newRoom: 840)
	else
		(if local0 (Bset 6))
		(curRoom newRoom: prevRoomNum)
	)
)

(class CombatIcon of IconItem
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state $0000
		cursor -1
		type $4000
		message -1
		modifiers $0000
		signal $0001
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		noun 0
		modNum 0
		helpVerb 0
		ducking 0
		calledBy 0
		autoDodging 0
	)
	
	(method (highlight param1)
		(if (or (not (& signal $0020)) (& signal $0004))
			(return)
		)
		(if param1
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (not (& signal $0004))
				(if
					(and
						(>= (theObjOrX x?) nsLeft)
						(>= (theObjOrX y?) nsTop)
						(<= (theObjOrX x?) nsRight)
						(<= (theObjOrX y?) nsBottom)
						(not
							(IsItSkip
								view
								loop
								cel
								(- (theObjOrX y?) nsTop)
								(- (theObjOrX x?) nsLeft)
							)
						)
					)
					(if (not cursor) (= cursor 1) (self highlight: 1))
					(return 1)
				else
					(if cursor (= cursor 0) (self highlight: 0))
					(return 0)
				)
			else
				(return 0)
			)
		)
	)
	
	(method (cue)
		(if
			(and
				(or
					(== (gWarriorObj view?) 26)
					(== (gWarriorObj view?) 27)
					(== (gWarriorObj view?) 555)
				)
				(not ducking)
			)
			(= ducking 1)
			(duckTimer setTicks: 60 self)
		else
			(if
				(and
					(gWarriorObj cel?)
					(== (gWarriorObj cel?) (gWarriorObj lastCel:))
					(not ducking)
				)
				(egoNoise number: 938 play:)
				(gMonster getHurt:)
				(splotch show: setCycle: EndLoop self)
			)
			(= ducking 0)
			(gWarriorObj normalize:)
		)
	)
	
	(method (warriorCast param1 param2 &tmp temp0)
		(cond 
			((< [egoStats 18] [spellCost (- param1 19)]) (return))
			((ego castSpell: param1)
				(= temp0 param2)
				(egoMPStat update:)
				(gWarriorObj cel: 0 setCycle: EndLoop temp0)
			)
		)
	)
	
	(method (tryAttack theTheCombatIcon param2 &tmp theCombatIcon temp1 temp2 temp3)
		(ego trySkill: 5 200)
		(egoSPStat update:)
		(egoHPStat update:)
		(= theCombatIcon 0)
		(if argc (= theCombatIcon theTheCombatIcon))
		(cond 
			(
				(>
					(= temp1
						(+
							(-
								(self attackLevel: param2)
								(gMonster defenseLevel: 0)
							)
							50
						)
					)
					80
				)
				(= temp1 80)
			)
			((< temp1 20) (= temp1 20))
		)
		(= temp2 (- temp1 (Random 1 100)))
		(gWarriorObj cel: 0)
		(if (or (== calledBy 2) (== calledBy 0))
			(= theCombatIcon CombatIcon)
		)
		(if (> temp2 (= calledBy 0))
			(gWarriorObj setCycle: EndLoop theCombatIcon)
		else
			(if (< (= temp3 (- (gWarriorObj lastCel:) 1)) 1)
				(= temp3 1)
			)
			(gWarriorObj setCycle: CycleTo temp3 1 theCombatIcon)
		)
	)
	
	(method (attackLevel &tmp temp0)
		(= temp0
			(/
				(+
					(* [egoStats 5] 8)
					(* [egoStats 2] 4)
					(* [egoStats 0] 2)
					[egoStats 1]
					[egoStats 4]
				)
				16
			)
		)
		(if (< [egoStats 17] 100)
			(= temp0 (- temp0 (/ (- 100 [egoStats 17]) 5)))
		)
		(return temp0)
	)
	
	(method (defend theTheCombatIcon &tmp theCombatIcon)
		(= theCombatIcon 0)
		(if argc
			(if
				(or
					(== (= theCombatIcon theTheCombatIcon) iconC)
					(== global420 24)
				)
				(ego trySkill: 7 200)
			else
				(ego trySkill: 6 200)
			)
		)
		(egoSPStat update:)
		(egoHPStat update:)
		(if
		(or (== calledBy 2) (== calledBy 0) autoDodging)
			(= autoDodging 0)
			(= theCombatIcon CombatIcon)
		)
		(= calledBy 0)
		(gWarriorObj cel: 0 setCycle: CycleTo 3 1 theCombatIcon)
	)
)

(class WarriorObj of Prop
	(properties
		x 100
		y 149
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 12
		underBits 0
		signal $4010
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		status 0
		weapValue 0
	)
	
	(method (cue)
		(switch status
			(0
				(if (cast contains: flames) (flames hide:))
				(= status 1)
				(self
					view: global421
					loop: 1
					cel: 0
					setCycle: CycleTo 1 1 self
				)
				(egoNoise number: 901 play:)
			)
			(1
				(= status 0)
				(self normalize:)
			)
		)
	)
	
	(method (getHurt param1 &tmp temp0)
		(gWarriorObj status: 0 cue:)
		(= temp0 (/ (+ [egoStats 6] [egoStats 7]) 6))
		(= temp0 (- param1 (/ (* param1 temp0) 100)))
		(if (ego has: 5)
			(= temp0 (- temp0 (- 6 arcadeDifficulty)))
		)
		(if (> (= temp0 (+ temp0 (Random 0 10))) 0)
			(= [egoStats 16] (- [egoStats 16] temp0))
			(if (< [egoStats 16] 1) (= [egoStats 16] 0))
			(egoHPStat update:)
		)
	)
	
	(method (normalize)
		(gWarriorObj view: global420 loop: 0 cel: 0 stopUpd:)
		(if (cast contains: flames)
			(flames show: setCycle: Forward)
		)
	)
	
	(method (autoDodge &tmp temp0)
		(if (== arcadeDifficulty 3) (return))
		(if (== arcadeDifficulty 1)
			(= temp0 5)
		else
			(= temp0 10)
		)
		(if (< (Random 0 100) (/ [egoStats 7] temp0))
			(iconC
				autoDodging: (if (== gCalledBy 0) 0 else 2)
				select:
			)
			(return)
		)
		(if (< (Random 0 100) (/ [egoStats 6] temp0))
			(iconD
				autoDodging: (if (== gCalledBy 0) 0 else 2)
				select:
			)
		)
	)
)

(class MonsterProp of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $4010
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
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
	)
	
	(method (init)
		(= cycleSpeed (/ 24 arcadeDifficulty))
		(super init: &rest)
	)
	
	(method (cue)
		(self setCel: 0 stopUpd:)
	)
)

(class Monster of MonsterProp
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $4010
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
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
		roar 0
		primDamage 0
		secDamage 0
		tertDamage 0
		armorValue 0
		monHP 0
		spellHitX 0
		spellHitY 0
		zapped 0
		picture 0
		mustFight 0
	)
	
	(method (init)
		(= gMonster self)
		(super init: &rest)
	)
	
	(method (getHurt param1 param2 param3 &tmp temp0)
		(if (< argc 3)
			(if
				(<
					(= temp0
						(-
							(+ (gWarriorObj weapValue?) (/ [egoStats 0] 10))
							(gMonster armorValue?)
						)
					)
					0
				)
				(= temp0 (/ (gWarriorObj weapValue?) 5))
			)
			(= temp0 (+ temp0 (Random 1 10)))
			(if (> argc 1)
				(if (and (< 0 param2) (< param2 4))
					(= temp0 (/ temp0 param2))
				else
					(= temp0 (+ temp0 param2))
				)
			)
			(= temp0 (+ temp0 zapPower))
			(= zapPower 0)
		else
			(= temp0 param3)
		)
		(if (spellTimer seconds?)
			(spellTimer seconds: 0 client: 0)
			(gMonster restart:)
		)
		(if
		(< (= monsterHealth (- monsterHealth temp0)) 0)
			(= monsterHealth 0)
		)
		(monsterHPStat update:)
	)
	
	(method (whimper)
		(if roar (globalSound number: roar play:))
	)
	
	(method (defenseLevel)
		(return 280)
	)
	
	(method (spellHurt param1 &tmp temp0)
		(= temp0 0)
		(switch param1
			(25
				(= temp0 (+ 10 (/ [egoStats 25] 30)))
				(gMonster getHurt: 0 0 temp0)
			)
			(27
				(= temp0 (+ 8 (/ [egoStats 27] 30)))
				(gMonster getHurt: 0 0 temp0)
			)
			(32
				(= temp0 (+ 12 (/ [egoStats 32] 30)))
				(gMonster getHurt: 0 0 temp0)
			)
			(22
				(Palette PALIntensity 0 255 500)
				(Palette PALIntensity 0 255 100)
				(self setCycle: 0 setScript: 0)
				(spellTimer setReal: combatRm (/ [egoStats 22] 10))
			)
		)
	)
	
	(method (restart)
	)
)

(class StatusBar of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 553
		loop 1
		cel 0
		priority 1
		underBits 0
		signal $4810
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
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
		xStep 1
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		statMax 0
		xMax 0
	)
	
	(method (init)
		(= xMax (self x?))
		(self x: (self calcVal:))
		(super init: &rest)
	)
	
	(method (calcVal param1 &tmp temp0)
		(= temp0 (/ (* param1 100) statMax))
		(= temp0 (- 47 (/ (* temp0 47) 100)))
		(if (and (> param1 1) (>= temp0 47)) (= temp0 46))
		(return (- xMax temp0))
	)
	
	(method (update param1 &tmp temp0)
		(self
			setMotion: MoveTo (self calcVal: param1) (self y?) self
		)
	)
)

(instance combatRm of Room
	(method (init)
		(if (== prevRoomNum 100) (= prevRoomNum 400))
		(= local0 (Btst 6))
		(Bclr 6)
		(globalSound setLoop: 1)
		(soundFx setLoop: 1)
		(= battleResult 2)
		((= gWarriorObj WarriorObj) view: 999 init:)
		(if
			(not
				(OneOf
					prevRoomNum
					400
					650
					700
					800
					810
					820
					830
					549
					851
					852
					853
				)
			)
			((ScriptID
				(Print
					addText: 4 0 1 1 0 12
					addButton: 560 4 0 0 2 0 26
					addButton: 565 4 0 0 3 140 26
					addButton: 575 4 0 0 5 0 40
					addButton: 580 4 0 0 6 140 40
					addButton: 585 4 0 0 7 0 54
					addButton: 590 4 0 0 8 140 54
					addButton: 595 4 0 0 9 0 68
					addButton: 845 4 0 0 10 140 68
					addButton: 855 4 0 0 11 0 82
					addButton: 860 4 0 0 12 140 82
					init:
				)
			)
				init:
			)
		else
			((ScriptID monsterNum) init:)
		)
		(Load RES_VIEW 553)
		(cond 
			((and (ego has: 1) ((inventory at: 1) state?))
				(gWarriorObj weapValue: 35)
				(= gCalledBy 0)
				(= global420 25)
				(= global421 555)
				(LoadMany 128 25 554 555)
				(flames init: setCycle: Forward)
			)
			((ego has: 1)
				(gWarriorObj weapValue: 20)
				(= gCalledBy 0)
				(= global420 23)
				(= global421 26)
				(LoadMany 128 23 26)
			)
			(else
				(gWarriorObj weapValue: 10)
				(= gCalledBy 2)
				(= global420 24)
				(= global421 27)
				(LoadMany 128 24 27 28 22 552)
				(LoadMany 130 21)
				(combatSpell init: hide:)
			)
		)
		(gWarriorObj view: global420)
		(theIconBar disable:)
		(splotch init: hide:)
		(HandsOn)
		(super init: &rest)
		(if (not monsterHealth)
			(= monsterHealth (gMonster monHP?))
		)
		(egoStatus init:)
		(monStatus init:)
		(egoHPStat
			statMax: (if (ego maxHealth:) (ego maxHealth:) else 100)
			init:
		)
		(egoSPStat
			statMax: (if (ego maxStamina:) (ego maxStamina:) else 100)
			init:
		)
		(egoMPStat
			statMax: (if (ego maxMana:) (ego maxMana:) else 100)
			init:
		)
		(monsterHPStat statMax: (gMonster monHP?) init:)
		(stamTimer setReal: stamTimer 5)
		(combatControls init: show: dispose:)
	)
	
	(method (dispose)
		(LoadMany 0 565 575 580 585 845 590 560 595 855 860)
		(UnLoad 128 24)
		(UnLoad 128 27)
		(UnLoad 128 28)
		(UnLoad 128 22)
		(UnLoad 128 552)
		(UnLoad 128 23)
		(UnLoad 128 26)
		(UnLoad 128 25)
		(UnLoad 128 554)
		(UnLoad 128 555)
		(UnLoad 128 553)
		(super dispose: &rest)
	)
	
	(method (cue)
		(gMonster restart:)
	)
)

(instance combatControls of GloryControls
	(properties)
	
	(method (init)
		(super init: &rest)
		(= icon1 iconA)
		(= icon2 iconB)
		(= icon3 iconC)
		(= icon4 iconD)
		(= icon5 iconE)
		(= icon6 iconF)
		(= icon7 iconMiddle)
		(self
			add: iconA iconB iconC iconD iconE iconF iconMiddle
			eachElementDo: #cel 0
			eachElementDo: #cursor 999
			eachElementDo: #signal 129
		)
		(cond 
			((== gCalledBy 2)
				(if (== heroType 1)
					(= gCalledBy 1)
					(if (gMonster mustFight?)
						(combatControls disable: iconF)
					)
					(if (not [egoStats 23]) (combatControls disable: iconE))
					(if (not [egoStats 32]) (combatControls disable: iconA))
					(if (not [egoStats 24]) (combatControls disable: iconB))
					(if (not [egoStats 25]) (combatControls disable: iconC))
					(if (not [egoStats 27]) (combatControls disable: iconD))
					(combatControls
						eachElementDo: #view 552
						eachElementDo: #cel 0
					)
				else
					(self eachElementDo: #view 551)
					(if (not [egoStats 12])
						(iconMiddle view: 550)
						(if (gMonster mustFight?) (self disable: iconMiddle))
					)
				)
			)
			((gMonster mustFight?) (self disable: iconMiddle))
		)
		(SetCursor 137 242 196 318)
		(theGame setCursor: theCursor 1 290 170)
	)
	
	(method (show)
		(= window combatWin)
		(User input: 1)
		(theGame setCursor: 999)
		(super show: &rest)
	)
	
	(method (dispatchEvent event &tmp [temp0 3])
		(if
			(and
				(!= (= gameTime (GetTime)) theGameTime)
				(> reversalTimer 0)
			)
			(-- reversalTimer)
			(= theGameTime gameTime)
		)
		(super dispatchEvent: event &rest)
		(sounds eachElementDo: #check)
		(timers eachElementDo: #doit)
		(Animate (cast elements?) 1)
		(if doMotionCue
			(= doMotionCue 0)
			(cast eachElementDo: #motionCue)
		)
	)
)

(instance combatWin of Window
	(properties
		top 125
		left 239
		bottom 189
		right 320
		type $0083
	)
	
	(method (open)
		(super open: &rest)
		(DrawCel 550 0 0 0 0 -1)
	)
)

(instance iconA of CombatIcon
	(properties
		view 550
		loop 1
		nsLeft 2
		nsTop 2
		maskView 552
		maskLoop 8
		maskCel 3
	)
	
	(method (select)
		(= calledBy gCalledBy)
		(if (cast contains: flames) (flames hide:))
		(gWarriorObj view: global420)
		(switch calledBy
			(0
				(gWarriorObj loop: 1)
				(self tryAttack: self)
			)
			(2
				(gWarriorObj loop: 1)
				(self tryAttack: self)
			)
			(1
				(gWarriorObj view: 28 loop: 0)
				(self warriorCast: 32 self)
			)
		)
		(return 1)
	)
	
	(method (cue)
		(switch gCalledBy
			(1
				(egoNoise number: 11 play:)
				(combatSpell
					x: 123
					y: 87
					setLoop: 2
					show:
					setCycle: Forward
					approachDist: 32
					setStep: 6 6
					setMotion:
						MoveTo
						(gMonster spellHitX?)
						(gMonster spellHitY?)
						combatSpell
				)
			)
			(else  (super cue:))
		)
		(gWarriorObj normalize:)
	)
)

(instance iconB of CombatIcon
	(properties
		view 550
		loop 2
		nsLeft 50
		nsTop 2
		signal $4000
		maskView 552
		maskLoop 8
		maskCel 4
	)
	
	(method (select)
		(= calledBy gCalledBy)
		(gWarriorObj view: global420)
		(if (cast contains: flames)
			(flames hide:)
			(gWarriorObj view: 554)
		)
		(switch calledBy
			(0
				(gWarriorObj loop: 2)
				(self tryAttack: self)
			)
			(2
				(gWarriorObj loop: 2)
				(self tryAttack: self)
			)
			(1
				(gWarriorObj view: 28 loop: 0)
				(self warriorCast: 22 self)
			)
		)
		(return 1)
	)
	
	(method (cue)
		(switch gCalledBy
			(1
				(egoNoise number: 942 play:)
				(combatSpell
					x: 123
					y: 87
					show:
					setLoop: 3
					approachDist: 22
					setStep: 6 6
					setCycle: EndLoop combatSpell
				)
			)
			(else  (super cue:))
		)
		(gWarriorObj normalize:)
	)
)

(instance iconC of CombatIcon
	(properties
		view 550
		loop 3
		nsLeft 2
		nsTop 34
		maskView 552
		maskLoop 8
		maskCel 5
	)
	
	(method (select &tmp theAutoDodging)
		(= calledBy gCalledBy)
		(if autoDodging
			(= theAutoDodging autoDodging)
		else
			(= theAutoDodging calledBy)
		)
		(if (cast contains: flames) (flames hide:))
		(gWarriorObj view: global421)
		(switch theAutoDodging
			(0
				(gWarriorObj loop: 1)
				(self defend: self)
			)
			(2
				(gWarriorObj loop: 0)
				(self defend: self)
			)
			(1
				(gWarriorObj view: 28 loop: 0)
				(self warriorCast: 25 self)
			)
		)
		(return 1)
	)
	
	(method (cue)
		(if (== gCalledBy 1)
			(egoNoise number: 13 play:)
			(combatSpell
				x: 123
				y: 87
				setLoop: 0
				show:
				setCycle: Forward
				approachDist: 25
				setStep: 6 6
				setMotion:
					MoveTo
					(gMonster spellHitX?)
					(gMonster spellHitY?)
					combatSpell
			)
			(gWarriorObj normalize:)
		else
			(super cue:)
		)
	)
)

(instance iconD of CombatIcon
	(properties
		view 550
		loop 4
		nsLeft 52
		nsTop 34
		maskView 552
		maskLoop 8
		maskCel 6
	)
	
	(method (select &tmp theAutoDodging)
		(= calledBy gCalledBy)
		(if autoDodging
			(= theAutoDodging autoDodging)
		else
			(= theAutoDodging calledBy)
		)
		(if (cast contains: flames) (flames hide:))
		(gWarriorObj view: global421)
		(switch theAutoDodging
			(0
				(gWarriorObj loop: 0)
				(self defend: self)
			)
			(2
				(gWarriorObj loop: 1)
				(self defend: self)
			)
			(1
				(gWarriorObj view: 28 loop: 0)
				(self warriorCast: 27 self)
			)
		)
		(return 1)
	)
	
	(method (cue)
		(if (== gCalledBy 1)
			(egoNoise number: 943 play:)
			(combatSpell
				x: 123
				y: 87
				setLoop: 1
				show:
				setCycle: Forward
				approachDist: 27
				setStep: 6 6
				setMotion:
					MoveTo
					(gMonster spellHitX?)
					(gMonster spellHitY?)
					combatSpell
			)
			(gWarriorObj normalize:)
		else
			(super cue:)
		)
	)
)

(instance iconE of CombatIcon
	(properties
		view 550
		loop 5
		nsLeft 3
		nsTop 12
		maskView 552
		maskLoop 8
		maskCel 1
	)
	
	(method (select)
		(= calledBy gCalledBy)
		(gWarriorObj view: global421)
		(switch calledBy
			(0 0)
			(2 0)
			(1
				(if (cast contains: flames) (flames hide:))
				(gWarriorObj view: 28 loop: 0)
				(self warriorCast: 23 self)
			)
		)
		(return 1)
	)
	
	(method (cue)
		(switch gCalledBy
			(1
				(egoNoise number: 900 play:)
				(= zapPower (+ 5 (/ [egoStats 23] 10)))
			)
			(else  (super cue:))
		)
		(gWarriorObj normalize:)
	)
)

(instance iconF of CombatIcon
	(properties
		view 550
		loop 6
		nsLeft 58
		nsTop 11
		maskView 552
		maskLoop 8
		maskCel 2
	)
	
	(method (select)
		(if (& signal $0004) (return 0))
		(= calledBy gCalledBy)
		(gWarriorObj view: global421)
		(switch calledBy
			(0 0)
			(2 0)
			(1 (localproc_03a1))
		)
		(return 1)
	)
)

(instance iconMiddle of CombatIcon
	(properties
		view 550
		loop 7
		nsLeft 23
		nsTop 2
		maskView 550
		maskLoop 8
	)
	
	(method (select)
		(if (& signal $0004) (return 0))
		(= calledBy gCalledBy)
		(switch calledBy
			(0 (localproc_03a1))
			(2
				(if (not [egoStats 12])
					(localproc_03a1)
				else
					(= gCalledBy 1)
					(if (gMonster mustFight?)
						(combatControls disable: iconF)
					)
					(if (not [egoStats 23]) (combatControls disable: iconE))
					(if (not [egoStats 32]) (combatControls disable: iconA))
					(if (not [egoStats 24]) (combatControls disable: iconB))
					(if (not [egoStats 25]) (combatControls disable: iconC))
					(if (not [egoStats 27]) (combatControls disable: iconD))
					(combatControls
						eachElementDo: #view 552
						eachElementDo: #cel 0
						eachElementDo: #show
					)
				)
			)
			(1
				(= gCalledBy 2)
				(combatControls
					enable: iconA iconB iconC iconD iconE iconF
					eachElementDo: #view 551
					eachElementDo: #cel 0
					eachElementDo: #show
				)
			)
		)
		(Animate (cast elements?) 1)
		(return 1)
	)
)

(instance combatSpell of Actor
	(properties
		x 123
		y 87
		view 22
		priority 14
		signal $4010
		moveSpeed 0
	)
	
	(method (cue)
		(if (< loop 3)
			(self loop: (+ loop 3) setCel: 0 setCycle: EndLoop self)
			(egoNoise number: 930 play:)
			(gMonster spellHurt: approachDist)
		else
			(if (== approachDist 22)
				(gMonster spellHurt: approachDist)
			)
			(self hide: setCycle: 0)
		)
	)
)

(instance flames of Prop
	(properties
		x 100
		y 149
		view 554
		priority 14
		signal $4010
	)
)

(instance egoStatus of View
	(properties
		x 30
		y 15
		view 553
		cel 1
		priority 3
		signal $4010
	)
)

(instance monStatus of View
	(properties
		x 260
		y 15
		view 553
		priority 3
		signal $4010
	)
)

(instance egoHPStat of StatusBar
	(properties
		x 30
		y 15
	)
	
	(method (cue)
		(if (< [egoStats 16] 1)
			(= battleResult 0)
			(localproc_03a1)
		)
	)
	
	(method (calcVal)
		(super calcVal: [egoStats 16])
	)
	
	(method (update)
		(super update: [egoStats 16])
	)
)

(instance egoSPStat of StatusBar
	(properties
		x 30
		y 26
	)
	
	(method (calcVal)
		(super calcVal: [egoStats 17])
	)
	
	(method (update)
		(super update: [egoStats 17])
	)
)

(instance egoMPStat of StatusBar
	(properties
		x 30
		y 37
	)
	
	(method (calcVal)
		(super calcVal: [egoStats 18])
	)
	
	(method (update)
		(super update: [egoStats 18])
	)
)

(instance monsterHPStat of StatusBar
	(properties
		x 260
		y 15
	)
	
	(method (cue)
		(cond 
			((== battleResult -1) (gWarriorObj setScript: sDemonEgoDies))
			((< monsterHealth 1) (= battleResult 1) (localproc_03a1))
		)
	)
	
	(method (calcVal)
		(super calcVal: monsterHealth)
	)
	
	(method (update)
		(super update: monsterHealth)
	)
)

(instance sDemonEgoDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(egoStatus hide:)
				(monStatus hide:)
				(egoHPStat hide:)
				(egoSPStat hide:)
				(egoMPStat hide:)
				(monsterHPStat hide:)
				(combatControls hide:)
				(= cycles 3)
			)
			(1
				(messager say: 1 6 1 0 0 840)
				(= cycles 3)
			)
			(2
				(= battleResult 1)
				(DisposeScript 40)
				(localproc_03a1)
			)
		)
	)
)

(instance splotch of Prop
	(properties
		view 553
		loop 2
		signal $4000
	)
	
	(method (show)
		(self
			setPri: (+ (gWarriorObj priority?) 1)
			x: (+
				(gWarriorObj x?)
				(if (== (gWarriorObj loop?) 1) 48 else 67)
			)
			y: (-
				(gWarriorObj y?)
				(if (== (gWarriorObj loop?) 1) 66 else 46)
			)
		)
		(super show: &rest)
	)
	
	(method (cue)
		(self hide: x: -50)
	)
)

(instance duckTimer of Timer
	(properties)
)

(instance spellTimer of Timer
	(properties)
)

(instance stamTimer of Timer
	(properties)
	
	(method (cue)
		(ego useStamina: -5)
		(egoSPStat update:)
		(self setReal: self 2)
	)
)

(instance egoNoise of Sound
	(properties)
)
