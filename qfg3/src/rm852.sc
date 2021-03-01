;;; Sierra Script 1.0 - (do not remove this comment)
(script# 852)
(include game.sh) (include "850.shm")
(use Main)
(use EgoDead)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm852 0
	paladinEndScript 1
	crankUpSword 2
	chuckSwordAtWiz 3
	lightnin1 4
	lightnin2 5
	orbTempts 6
)

(instance rm852 of Room
	(properties
		modNum 850
		noun N_ROOM
		picture 850
	)
	
	(method (init)
		(= heroType PALADIN)
		(Bset fWizNoticesEgo)
		(super init:)
		(self setRegions: 850)
		(if (== prevRoomNum 550)
			(self setScript: killedGarg)
		else
			(self setScript: paladinEndScript)
		)
		(if (not (== global155 0))
			(theGame save: TRUE)
		)
	)
)

(instance paladinEndScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 850 2) view: 863)
				(ego setMotion: MoveTo 20 183 self)
			)
			(1
				(if (Btst fSenseDanger)
					(messager say: N_REGION V_DOIT C_SENSE_DANGER 0 self 850)
				else
					(self cue:)
				)
			)
			(2
				(messager say: N_DEWIZ V_DOIT C_GREET_PALADIN 0 self 850)
			)
			(3
				((ScriptID 850 2) view: 863 loop: 6 setCycle: EndLoop self)
			)
			(4
				((ScriptID 850 3) setCycle: EndLoop self)
			)
			(5
				((ScriptID 850 3) loop: 1 setCycle: CycleTo 3 1 self)
			)
			(6
				(= monsterNum 855)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance killedGarg of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 850 3) loop: 2 cel: 0 noun: 4)
				(ego x: 20 y: 183 solvePuzzle: fBeatGargoyle 3 (| puzzleFIGHTER puzzlePALADIN))
				((ScriptID 850 2) view: 863 loop: (Random 0 6) cel: 0)
				(= seconds 2)
			)
			(1
				(if (== global155 0)
					(EgoDead C_DEATH_GARGOYLE 850)
				else
					(self cue:)
				)
			)
			(2
				((ScriptID 850 3) cycleSpeed: 3 setCycle: EndLoop self)
			)
			(3
				((ScriptID 850 3) view: 854 setLoop: 1)
				(messager say: 3 6 7 0 self 850)
			)
			(4
				((ScriptID 850 2) setScript: (ScriptID 850 8))
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_ACTIONS ICON_CAST)
				(self dispose:)
			)
		)
	)
)

(instance crankUpSword of Script
	
	(method (doit)
		(if
			(and
				(== (self script?) (ScriptID 850 12))
				(== ((ScriptID 850 12) state?) 0)
				(== (self state?) 0)
			)
			((ScriptID 850 12) cue:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bclr fDeWizBattle)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable: ICON_WALK ICON_TALK ICON_ACTIONS ICON_CAST)
				(Bset fDeWizElectrocuted)
				((ScriptID 850 8) dispose:)
				(ego setMotion: MoveTo 42 178 self)
			)
			(1
				(theIconBar disable: ICON_WALK ICON_TALK ICON_ACTIONS ICON_CAST)
				(if (IsObject (ego looper?))
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego setMotion: 0 setCycle: 0)
				(Face ego (ScriptID 850 2))
				((ScriptID 850 2)
					view: 863
					cel: 0
					loop: (Random 0 6)
					setCycle: EndLoop self
				)
			)
			(2
				((ScriptID 850 2)
					view: 863
					loop: 0
					setCycle: CycleTo 6 1 self
				)
			)
			(3
				((ScriptID 850 9)
					setLoop: (Random 0 4)
					x: 186
					y: 60
					cycleSpeed: 0
					moveSpeed: 0
					setScale:
					setStep: 8 7
					init:
					setMotion: MoveTo 61 111 self
				)
				((ScriptID 850 2) setCycle: EndLoop self)
			)
			(4
				(ego view: 38 loop: 0 cel: 0 setCycle: EndLoop)
				(sFx2 number: 240 play:)
			)
			(5
				(sFx2 number: 10 play:)
				((ScriptID 850 9) dispose:)
				(ego setCycle: BegLoop self)
			)
			(6
				(ego normalize:)
				(= state 0)
				(if (== (self script?) (ScriptID 850 12))
					((ScriptID 850 12) cue:)
				)
				(if (== ((ScriptID 850 3) script?) (ScriptID 850 7))
					((ScriptID 850 7) cue:)
				)
				(theIconBar enable: ICON_WALK ICON_TALK)
				(switch arcadeDifficulty
					(1 (= seconds 15))
					(2 (= seconds 10))
					(3 (= seconds 5))
				)
			)
		)
	)
)

(instance chuckSwordAtWiz of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 850 8) dispose:)
				(crankUpSword dispose:)
				(self setScript: (ScriptID 32 0) self 11)
				(ego drop: iSword)
				(Bset fChuckedSwordAtWiz)
			)
			(1)
			(2
				(sFx2 number: 910 play:)
				((ScriptID 850 2)
					view: 865
					loop: 0
					cel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
				(if (== (ego script?) (ScriptID 850 11))
					((ScriptID 850 11) cue:)
				)
				(if (== ((ScriptID 850 16) state?) 0)
					((ScriptID 850 16) cue:)
				)
			)
			(3
				((ScriptID 850 2)
					loop: 1
					cel: 0
					setPri: 14
					setCycle: EndLoop self
				)
			)
			(4
				((ScriptID 850 2)
					view: 865
					setLoop: 3
					cycleSpeed: 0
					setPri: 2
					setCycle: Forward
				)
				(lightnin2 init: setPri: 1 cycleSpeed: 0 setCycle: Forward)
				(sFx2 number: 7 play:)
				(self setScript: (ScriptID 850 10))
				((ScriptID 850 10) setScript: orbTempts)
				(if (== (ego script?) (ScriptID 850 11))
					(messager say: N_REGION V_DOIT C_GARGOYLE_RELEASES 0 0 850)
					((ScriptID 850 11) cue:)
				)
				(if (== ((ScriptID 850 16) state?) 0)
					((ScriptID 850 16) cue:)
				)
			)
		)
	)
)

(instance orbTempts of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 7))
			(1
				(messager say: N_REGION V_DOIT C_ORB_TEMPTS 0 self 850)
			)
			(2 (= seconds 7))
			(3
				(messager say: N_REGION V_DOIT C_ORB_SPEAKS_1 0 self 850)
			)
			(4 (= seconds 7))
			(5
				(messager say: N_REGION V_DOIT C_ORB_SPEAKS_2 0 self 850)
			)
			(6 (= seconds 7))
			(7
				(messager say: N_REGION V_DOIT C_ORB_SPEAKS_3 0 self 850)
			)
			(8
				(messager say: N_REGION V_DOIT C_ORB_SPEAKS_4 0 self 850)
			)
			(9 (= seconds 7))
			(10
				(messager say: N_REGION V_DOIT C_ORB_SPEAKS_5 0 self 850)
			)
			(11 (self dispose:))
		)
	)
)

(instance lightnin1 of Prop
	(properties
		x 265
		view 855
		loop 1
		priority 13
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		((ScriptID 850 4) doVerb: theVerb)
	)
)

(instance lightnin2 of Prop
	(properties
		x 204
		view 855
		loop 1
		cel 1
		priority 13
		signal (| ignrAct fixPriOn)
	)
)

(instance sFx2 of Sound)
