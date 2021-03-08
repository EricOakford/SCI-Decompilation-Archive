;;; Sierra Script 1.0 - (do not remove this comment)
(script# 853)
(include game.sh) (include "850.shm")
(use Main)
(use EgoDead)
(use OccasionalCycle)
(use Scaler)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm853 0
	mageEndScript 1
	deMageBattle 2
	deWizRecoils 3
)

(local
	beatenGargoyle
)
(instance rm853 of Room
	(properties
		modNum 850
		noun N_ROOM
		picture 850
	)
	
	(method (init)
		(super init:)
		(self setRegions: 850)
		(if (== prevRoomNum 550)
			(= beatenGargoyle TRUE)
			(Bset fWizNoticesEgo)
			(self setScript: fromFracas)
		else
			((ScriptID 850 6) init: stopUpd:)
			(self setScript: mageEndScript)
		)
		(if (not (== battleResult 0))
			(theGame save: 1)
		)
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(not (== (mageEndScript state?) 3))
				(== ((ScriptID 850 2) view?) 861)
				(== ((ScriptID 850 2) loop?) 0)
			)
			((ScriptID 850 2) loop: 1 cel: 0 setCycle: EndLoop)
		)
		(switch theVerb
			(V_REVERSAL
				(if (== (deMageBattle state?) 11)
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				)
				(cond 
					((== beatenGargoyle TRUE)
						(messager say: N_REGION V_DOIT C_REVERSAL_ALREADY_ACTIVE 0 0 850)
					)
					(
						(and
							(not (== beatenGargoyle TRUE))
							(not (== (self script?) egoSummon))
							(not (== (self script?) deMageBattle))
						)
						(= beatenGargoyle TRUE)
						((ScriptID 850 8) dispose:)
						(Bset fDeWizBattle)
						(self setScript: deMageBattle)
					)
				)
			)
			(V_FLAME
				(if (== (deMageBattle state?) 11)
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				)
				(if (ego script?)
					((ego script?) setScript: (ScriptID 850 12))
				else
					(ego setScript: (ScriptID 850 12) 0 theVerb)
				)
			)
			(V_FORCEBOLT
				(if (== (deMageBattle state?) 11)
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				)
				(if (ego script?)
					((ego script?) setScript: (ScriptID 850 12))
				else
					(ego setScript: (ScriptID 850 12) 0 theVerb)
				)
			)
			(V_LIGHTNING
				(if (== (deMageBattle state?) 11)
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				)
				(if (ego script?)
					((ego script?) setScript: (ScriptID 850 12))
				else
					(ego setScript: (ScriptID 850 12) 0 theVerb)
				)
			)
			(V_LEVITATE
				(if (== (deMageBattle script?) egoTakesDamage)
					(egoTakesDamage dispose:)
					(deMageBattle setScript: egoLevitates deMageBattle)
				else
					(messager say: N_REGION V_DOIT C_CANT_LEVITATE 0 0 850)
				)
			)
			(V_STAFF
				(if (== (deMageBattle state?) 11)
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				)
				(if (not (== (egoSummon state?) -1))
					(super doVerb: theVerb)
				)
				(if (not (Btst fDeWizTookStaff))
					(mageEndScript dispose:)
					((ScriptID 850 8) dispose:)
					(deMageBattle dispose:)
					(curRoom setScript: egoSummon)
				)
			)
			(V_CALM
				(if (== (deMageBattle script?) egoTakesDamage)
					(sFx3 number: 942 play:)
					(egoTakesDamage dispose:)
					(deMageBattle cue:)
				else
					(messager say: N_REGION V_DOIT C_SPELL_WONT_WORK 0 0 850)
				)
			)
			(V_TRIGGER
				(cond 
					((== (deMageBattle state?) 11)
						(EgoDead C_DEATH_FIRE 850 857 EndLoop)
					)
					((Btst fDeWizTookStaff)
						(curRoom setScript: kablooie)
					)
					((== (deMageBattle state?) 17)
						(deMageBattle state: -1)
						((deMageBattle client?) setScript: triggerGarg)
					)
					(else
						(messager say: N_REGION V_DOIT C_SPELL_WONT_WORK 0 0 850)
					)
				)
			)
			(V_DETECT
				(if (== (deMageBattle state?) 11)
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				else
					(messager say: N_REGION V_DOIT C_SPELL_WONT_WORK 0 0 850)
				)
			)
			(V_DAZZLE
				(if (== (deMageBattle state?) 11)
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				else
					(messager say: N_REGION V_DOIT C_SPELL_WONT_WORK 0 0 850)
				)
			)
			(V_JUGGLE
				(if (== (deMageBattle state?) 11)
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				else
					(messager say: N_REGION V_DOIT C_SPELL_WONT_WORK 0 0 850)
				)
			)
			(V_OPEN
				(messager say: N_REGION V_DOIT C_SPELL_WONT_WORK 0 0 850)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance triggerGarg of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 15 cel: 0 setCycle: EndLoop self)
				(globalSound setLoop: 1 number: 900 play:)
			)
			(1
				(ego setCycle: BegLoop self)
			)
			(2
				(ego normalize:)
				(client setScript: fromFracas)
			)
		)
	)
)

(instance deWizRecoils of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 850 2)
					view: 866
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				((ScriptID 850 2) setCycle: BegLoop self)
			)
			(2
				((ScriptID 850 2) view: 863)
				(messager say: N_DEWIZ V_DOIT C_WIZ_RECOILS 0 self 850)
			)
			(3
				(if (== (egoSummon state?) 4) (egoSummon seconds: 1))
				(self dispose:)
			)
		)
	)
)

(instance kablooie of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr fDeWizTookStaff)
				(ego view: 15 cel: 0 setCycle: EndLoop self)
				(globalSound number: 944 setLoop: 1 play:)
			)
			(1 (ego setCycle: BegLoop self))
			(2
				(ego normalize:)
				((ScriptID 850 2) loop: 2 cel: 0 setCycle: EndLoop)
				(stick setMotion: MoveTo 183 68)
				(= seconds 3)
			)
			(3
				(stick dispose:)
				((ScriptID 850 17) number: 930 play:)
				(kaBoom init: setPri: 14 setCycle: CycleTo 5 1 self)
			)
			(4
				((ScriptID 850 2) dispose:)
				(kaBoom setCycle: EndLoop self)
			)
			(5
				(kaBoom
					setLoop: 1
					x: 193
					y: 77
					setScale: Scaler 22 150 96 34
					cycleSpeed: 10
					setCycle: Forward
					setMotion: JumpTo 67 174 self
				)
			)
			(6
				((ScriptID 850 17) number: 403 play:)
				(kaBoom setMotion: JumpTo 44 178 self)
			)
			(7
				((ScriptID 850 17) number: 403 play:)
				(kaBoom setCycle: 0)
				(= seconds 3)
			)
			(8
				(ego setMotion: MoveTo 22 176 self)
			)
			(9
				(ego setLoop: 0 setCycle: CycleTo 3 1 self)
			)
			(10
				((ScriptID 850 17) number: 920 play:)
				(kaBoom setMotion: JumpTo 144 189 self)
			)
			(11
				(ego solvePuzzle: fBeatDeWiz 10 normalize:)
				(kaBoom dispose:)
				(HandsOn)
				((ScriptID 32 0) start: 0)
				(self setScript: portalTimer)
			)
		)
	)
)

(instance portalTimer of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch arcadeDifficulty
					(1 (= seconds 180))
					(2 (= seconds 30))
					(3 (= seconds 15))
				)
			)
			(1
				(switch arcadeDifficulty
					(1 (= seconds 180))
					(2 (= seconds 30))
					(3 (= seconds 20))
				)
			)
			(2
				(HandsOff)
				((ScriptID 850 6) init:)
				(DrawPic 850 PIXELDISSOLVE)
				(EgoDead C_DEATH_THERMONUCLEAR 850 857 EndLoop)
			)
		)
	)
)

(instance egoSummon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fWizNoticesEgo)
				(if (cast contains: (ScriptID 850 6))
					(self setScript: deMasterBails self)
				else
					(self cue:)
				)
			)
			(1
				(HandsOff)
				(ego view: 20 loop: 4 cel: 0 setCycle: EndLoop self)
				((ScriptID 850 17) number: 900 play:)
			)
			(2
				(ego setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(3
				(messager say: N_DEWIZ V_DOIT C_SUMMON_STAFF 0 self 850)
			)
			(4
				(theIconBar enable: ICON_CAST ICON_LOOK ICON_DO ICON_CONTROL)
				(theIconBar curIcon: (theIconBar at: ICON_LOOK))
				(theGame setCursor: 941)
				(user canInput: TRUE)
				(Bset fCastingSpell)
				((ScriptID 32 0) start: 1)
				(= seconds 10)
			)
			(5
				(Bset fDeWizTookStaff)
				(HandsOff)
				((ScriptID 850 2)
					view: 864
					loop: 0
					cel: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(6
				((ScriptID 850 17) number: 12 play:)
				((ScriptID 850 9)
					view: 21
					setLoop: 7
					x: 167
					y: 81
					init:
					setCycle: Forward
					setMotion: JumpTo 23 143 self
				)
			)
			(7
				(ego view: 20 loop: 6 cel: 0 setCycle: CycleTo 2 1)
				((ScriptID 850 9) setMotion: MoveTo 17 128)
				(= seconds 4)
			)
			(8
				(ego setLoop: 4 cel: 255 setCycle: BegLoop)
				(stick init: setLoop: 1 setMotion: JumpTo 183 63)
				((ScriptID 850 9) setMotion: JumpTo 169 81 self)
			)
			(9
				(ego normalize:)
				((ScriptID 850 9) dispose:)
				((ScriptID 850 2) setCycle: EndLoop self)
			)
			(10
				((ScriptID 850 2) loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(11
				(messager say: N_DEWIZ V_DOIT C_WIZ_FETCHES_STAFF 0 self 850)
			)
			(12
				(ego drop: iWood)
				(user canInput: TRUE)
				(theIconBar enable: ICON_DO ICON_LOOK ICON_INVENTORY ICON_CAST)
				(theIconBar curIcon: (theIconBar at: 2))
				(theGame setCursor: 941)
				(= seconds 5)
			)
			(13
				(messager say: N_DEWIZ V_DOIT C_WIZ_HAS_STAFF 0 self 850)
			)
			(14 (= seconds 5))
			(15
				(messager say: N_DEWIZ V_DOIT C_START_SUMMON 0 self 850)
			)
			(16
				(stick dispose:)
				((ScriptID 850 2)
					view: 861
					loop: 1
					cel: 5
					cycleSpeed: 10
					setCycle: BegLoop self
				)
			)
			(17
				((ScriptID 850 2)
					loop: 2
					setCycle: OccasionalCycle self 1 10 30
				)
				(= seconds 5)
			)
			(18
				((ScriptID 850 2) setCycle: 0)
				((ScriptID 850 6) init:)
				(DrawPic 850 PIXELDISSOLVE)
				(= seconds 2)
			)
			(19
				(EgoDead C_DEATH_THERMONUCLEAR 850 857 EndLoop)
			)
		)
	)
)

(instance egoLevitates of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 17 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(1
				((ScriptID 850 17) number: 281 play:)
				(ego
					setCel: 255
					setScale:
					setMotion: MoveTo (ego x?) (- (ego y?) 50) self
				)
			)
			(2 (= seconds 2))
			(3
				(floorFire dispose:)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 50) self)
			)
			(4
				(ego setScale: Scaler 92 63 189 130 setCycle: BegLoop self)
			)
			(5
				(ego normalize:)
				(user canInput: TRUE)
				(theIconBar enable: ICON_LOOK ICON_TALK ICON_CAST ICON_DO ICON_INVENTORY)
				(= state -1)
				(self dispose:)
			)
		)
	)
)

(instance mageEndScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 1)
			)
			(1
				(messager say: N_DELORD V_DOIT C_DEMONS_TALK 0 self 850)
			)
			(2
				(ego setMotion: MoveTo 20 183 self)
			)
			(3
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_ACTIONS)
				(switch arcadeDifficulty
					(1 (= seconds 60))
					(2 (= seconds 30))
					(3 (= seconds 20))
				)
			)
			(4
				(if (cast contains: (ScriptID 850 6))
					(self setScript: deMasterBails self)
				else
					(self cue:)
				)
			)
			(5
				(user canInput: TRUE)
				((ScriptID 850 2) setCycle: EndLoop self)
			)
			(6
				((ScriptID 850 2) loop: 1 setCycle: EndLoop self)
			)
			(7
				(messager say: N_DEWIZ V_DOIT C_NOTICE_EGO2 0 self 850)
			)
			(8
				(theIconBar enable: ICON_DO ICON_CAST ICON_INVENTORY)
				(theGame setCursor: 941)
				((ScriptID 850 2) setScript: (ScriptID 850 8))
				(self dispose:)
			)
		)
	)
)

(instance deMageBattle of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (cast contains: (ScriptID 850 6))
					(self setScript: deMasterBails self)
				else
					(self cue:)
				)
			)
			(1
				(HandsOff)
				((ScriptID 850 2)
					view: 863
					loop: 0
					setCycle: CycleTo 6 1 self
				)
			)
			(2
				(ego view: 15 loop: 2 cel: 0 setCycle: EndLoop)
				((ScriptID 850 17) number: 943 play:)
				((ScriptID 850 9)
					setLoop: (Random 0 4)
					x: 186
					y: 60
					setStep: 8 7
					cycleSpeed: 0
					moveSpeed: 0
					setScale:
					init:
					setMotion: MoveTo (ego x?) (- (ego y?) 35) self
				)
				((ScriptID 850 2) setCycle: EndLoop)
				((ScriptID 850 17) number: 13 play:)
			)
			(3
				(ego normalize: 6)
				((ScriptID 850 17) number: 10 play:)
				((ScriptID 850 9)
					setMotion:
						MoveTo
						((ScriptID 850 2) x?)
						(- ((ScriptID 850 2) y?) 35)
						self
				)
			)
			(4
				((ScriptID 850 9) loop: 10 setCycle: EndLoop self)
				((ScriptID 850 17) number: 930 play:)
			)
			(5
				((ScriptID 850 9) dispose:)
				((ScriptID 850 2)
					view: 868
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				((ScriptID 850 2) setCycle: BegLoop self)
			)
			(7
				((ScriptID 850 2) view: 863 loop: 0 cel: 0)
				(messager say: N_DEWIZ V_DOIT 16 0 self 850)
			)
			(8
				((ScriptID 850 2) setCycle: EndLoop self)
			)
			(9
				((ScriptID 850 2) setCycle: BegLoop self)
			)
			(10
				(messager say: N_DEWIZ V_DOIT C_BURN 0 self 850)
			)
			(11
				((ScriptID 850 17) number: 101 play:)
				(floorFire x: (ego x?) y: (ego y?) init: setCycle: Forward)
				(theIconBar enable: 8 2 6)
				(theGame setCursor: 941)
				(user canInput: 1)
				(self setScript: egoTakesDamage self)
			)
			(12
				(if (not (== (egoLevitates state?) -1)) (self cue:))
			)
			(13
				(ego normalize:)
				(floorFire dispose:)
				(messager say: N_DEWIZ V_DOIT C_GARGOYLE_SPELL_MAGE 0 self 850)
			)
			(14
				((ScriptID 850 2) setCycle: EndLoop self)
			)
			(15
				(HandsOff)
				((ScriptID 850 3)
					view: 852
					loop: 0
					cel: 0
					noun: 4
					setCycle: EndLoop self
				)
			)
			(16
				(sFx3 number: 900 play:)
				((ScriptID 850 3) loop: 1 setCycle: CycleTo 3 1 self)
			)
			(17
				(HandsOn)
				(theIconBar disable: ICON_ACTIONS)
				(= seconds 5)
			)
			(18
				(messager say: N_DEWIZ V_DOIT C_GARGOYLE_ATTACKS 0 self 850)
			)
			(19
				(= monsterNum 855)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance fromFracas of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fWizNoticesEgo)
				(ego x: 20 y: 183)
				((ScriptID 850 3) view: 852 loop: 2 cel: 0)
				((ScriptID 850 2) view: 863 loop: (Random 0 6) cel: 0)
				(= seconds 2)
			)
			(1
				(if (and (== prevRoomNum 550) (== battleResult 0))
					(EgoDead C_DEATH_GARGOYLE_MAGIC 850)
				else
					(self cue:)
				)
			)
			(2
				((ScriptID 850 3) cycleSpeed: 3 setCycle: EndLoop self)
			)
			(3
				((ScriptID 850 3) view: 854 setLoop: 1)
				(messager say: N_DEWIZ V_DOIT C_DEFEATED_GARGOYLE 0 self 850)
			)
			(4
				(messager say: N_DEWIZ V_DOIT C_SUMMON_MASTER 0 self 850)
			)
			(5
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_ACTIONS)
				((ScriptID 850 2)
					view: 861
					loop: 1
					cel: 255
					setCycle: BegLoop self
				)
			)
			(6
				((ScriptID 850 2) loop: 0 cel: 255 setCycle: EndLoop self)
			)
			(7
				((ScriptID 850 2) setCycle: OccasionalCycle self 1 10 25)
				(switch arcadeDifficulty
					(1 (= seconds 60))
					(2 (= seconds 40))
					(3 (= seconds 20))
				)
			)
			(8
				((ScriptID 850 2) setCycle: 0)
				((ScriptID 850 6) init:)
				(DrawPic 850 PIXELDISSOLVE)
				(= seconds 1)
			)
			(9
				(EgoDead C_DEATH_THERMONUCLEAR 850)
				(self dispose:)
			)
		)
	)
)

(instance egoTakesDamage of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 29 setCycle: Forward)
				(if (ego takeDamage: 10)
					(switch arcadeDifficulty
						(1 (= seconds 6))
						(2 (= seconds 4))
						(3 (= seconds 2))
					)
				else
					(EgoDead C_DEATH_FIRE 850 857 EndLoop)
				)
			)
			(1
				(self init:)
			)
		)
	)
)

(instance deMasterBails of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 3)
			)
			(1
				((ScriptID 850 6) dispose:)
				(DrawPic 850 PIXELDISSOLVE)
				(= seconds 2)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance floorFire of Prop
	(properties
		modNum 850
		view 872
		signal ignrAct
	)
)

(instance kaBoom of Actor
	(properties
		x 193
		y 97
		noun 5
		modNum 850
		view 867
		signal ignrAct
	)
)

(instance stick of Actor
	(properties
		x 20
		y 117
		view 790
		loop 1
	)
)

(instance sFx3 of Sound)
