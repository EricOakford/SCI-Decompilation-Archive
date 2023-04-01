;;; Sierra Script 1.0 - (do not remove this comment)
(script# ENDGAME)
(include game.sh) (include "600.shm")
(use Main)
(use Procs)
(use LoadMany)
(use Timer)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	EndGame 0
)

(local
	saveBits
	oldSortedFeatures
	local2
)
(instance EndGame of Room
	(properties
		picture rCastleAnteroom
		style VSHUTTER
	)
	
	(method (init)
		(Load SOUND (SoundFX sEndGame))
		(LoadMany VIEW vEndGameCast vEndGameDevs)
		(super init: &rest)
		(SolvePuzzle f600EndGame 25)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(theMusic stop:)
		(DoSound SoundOn TRUE)
		(theGame setSpeed: 6)
		(HandsOff)
		(theIconBar enable: ICON_LOOK)
		(user canInput: TRUE)
		(SetCurIcon ICON_LOOK)
		(arturo init: stopUpd:)
		(rogerHardy init: stopUpd:)
		(bobMallory init: stopUpd:)
		(tom init: stopUpd:)
		(oliver init: stopUpd:)
		(carlos init: stopUpd:)
		(loriCole init: stopUpd:)
		(coreyCole init: stopUpd:)
		(bobFischbach init: stopUpd:)
		(richardA init: stopUpd:)
		(jonBock init: stopUpd:)
		(jerryMoore init: stopUpd:)
		(gloria init: stopUpd:)
		(vana init: stopUpd:)
		(wilWong init: stopUpd:)
		(diana init: stopUpd:)
		(jeffCrowe init: stopUpd:)
		(ericKasner init: stopUpd:)
		(richardP init: stopUpd:)
		(ourHero init: stopUpd:)
		(baron init: stopUpd:)
		(yoric init: stopUpd:)
		(if (Btst fSavedElsa)
			((inventory at: iSilver)
				amount: (+ ((inventory at: iSilver) amount?) 1100)
			)
		)
		(narrator keepWindow: FALSE)
		;EO: For the MT-32 and General MIDI drivers, no tracks were given to play.
		; The Macintosh version fixed this oversight.
		(theMusic
			number: sEndGame
			loop: -1
			play:
		)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(self setScript: awardScript)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(= useSortedFeatures oldSortedFeatures)
		(super dispose:)
	)
)

(class lookView of View
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(user canInput: FALSE)
			(cond 
				((== loop 2) (= loop 3))
				((== loop 0) (= loop 1))
			)
			(nextRoomCue setReal: nextRoomCue 15)
			(flashBack setReal: self 3)
		)
	)
	
	(method (cue)
		(cond 
			((== loop 3) (= loop 2))
			((== loop 1) (= loop 0))
		)
		(Display 600 0 p_restore saveBits)
		(user canInput: TRUE)
	)
)

(instance flashBack of Timer)

(instance nextRoomCue of Timer
	(method (cue)
		(nextRoomCue dispose: delete:)
		(flashBack dispose: delete:)
		(curRoom newRoom: ENDGAME2)
	)
)

(instance ourHero of Actor
	(properties
		x 87
		y 137
		noun N_OURHERO
		view vEndGameCast
		loop 4
		signal fixedLoop
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(if local2
				(nextRoomCue dispose: delete:)
				(flashBack dispose: delete:)
				(curRoom newRoom: ENDGAME2)
			else
				(= local2 0)
				(messager say: N_OURHERO V_LOOK)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance baron of Prop
	(properties
		x 67
		y 130
		noun N_BARON
		view vEndGameCast
		loop 6
	)
	
	(method (init)
		(= nightPalette 1138)
		(PalVary PALVARYTARGET 1138)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: 2 1)
		)
	)
)

(instance centaurs of View
	(properties
		x 265
		y 161
		noun N_CENTAURS
		view vEndGameCast
		loop 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_CENTAURS V_LOOK)
		)
	)
)

(instance kattaAndAbdul of View
	(properties
		x 167
		y 143
		noun N_KATTASANDABDULLA
		view vEndGameCast
		loop 2
		cel 4
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_KATTASANDABDULLA V_LOOK)
		)
	)
)

(instance elsa of View
	(properties
		x 78
		y 118
		noun N_ELSA
		view vEndGameCast
		loop 1
		cel 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_ELSA V_LOOK)
		)
	)
)

(instance baronette of View
	(properties
		x 24
		y 136
		noun N_BARNARD
		view vEndGameCast
		loop 1
		cel 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_BARNARD V_LOOK)
		)
	)
)

(instance sheriffAndOtto of View
	(properties
		x 16
		y 170
		noun N_SHERIFFANDOTTO
		view vEndGameCast
		loop 2
		cel 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_SHERIFFANDOTTO V_LOOK)
		)
	)
)

(instance erasmusAndFenrus of View
	(properties
		x 183
		y 158
		noun N_ERASMUSANDFENRUS
		view vEndGameCast
		loop 2
		cel 1
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_ERASMUSANDFENRUS V_LOOK)
		)
	)
)

(instance hermitAndHealer of View
	(properties
		x 227
		y 153
		noun N_HERMITANDHEALER
		view vEndGameCast
		loop 2
		cel 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_HERMITANDHEALER V_LOOK)
		)
	)
)

(instance thiefAndCrusher of View
	(properties
		x 294
		y 134
		noun N_THIEFANDCRUSHER
		view vEndGameCast
		loop 2
		cel 5
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_THIEFANDCRUSHER V_LOOK)
		)
	)
)

(instance minotaur of View
	(properties
		x 123
		y 109
		noun N_MINOTAUR
		view vEndGameCast
		loop 1
		cel 1
		priority 4
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_MINOTAUR V_LOOK)
		)
	)
)

(instance shopkeeperAndZara of View
	(properties
		x 193
		y 163
		noun N_SHOPKEEPERANDZARA
		view vEndGameCast
		loop 2
		cel 9
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_SHOPKEEPERANDZARA V_LOOK)
		)
	)
)

(instance swordmaster of View
	(properties
		x 197
		y 184
		noun N_SWORDMASTER
		view vEndGameCast
		loop 2
		cel 6
		priority 15
		signal (| fixPriOn ignrAct)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_SWORDMASTER V_LOOK)
		)
	)
)

(instance guildmaster of View
	(properties
		x 167
		y 135
		noun N_GUILDMASTER
		view vEndGameCast
		loop 2
		cel 7
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_GUILDMASTER V_LOOK)
		)
	)
)

(instance grannyAndCat of View
	(properties
		x 196
		y 133
		noun N_GRANNYANDCAT
		view vEndGameCast
		loop 2
		cel 8
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_GRANNYANDCAT V_LOOK)
		)
	)
)

(instance karl of View
	(properties
		x 60
		y 91
		noun N_KARL
		view vEndGameCast
		loop 2
		cel 10
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_KARL V_LOOK)
		)
	)
)

(instance yoric of Actor
	(properties
		x 2
		y 147
		noun N_YORICK
		view vEndGameCast
		loop 5
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_YORICK V_LOOK)
		)
	)
)

(instance bartender of View
	(properties
		x 142
		y 139
		noun N_BARTENDER
		view vEndGameCast
		loop 2
		cel 11
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(narrator x: -1 y: -1)
			(messager say: N_BARTENDER V_LOOK)
		)
	)
)

(instance carlos of Prop
	(properties
		x 273
		y 189
		noun N_CARLOS
		view vEndGameDevs
		loop 2
		cel 1
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 73)
			(narrator x: 168 y: 131)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance loriCole of Prop
	(properties
		x 243
		y 150
		noun N_LORICOLE
		view vEndGameDevs
		cel 8
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 69)
			(narrator x: 175 y: 114)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance coreyCole of Prop
	(properties
		x 226
		y 140
		noun N_COREYCOLE
		view vEndGameDevs
		cel 9
		priority 12
		signal fixPriOn
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 65)
			(narrator x: 150 y: 110)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance oliver of Prop
	(properties
		x 216
		y 189
		noun N_OLIVER
		view vEndGameDevs
		loop 2
		cel 2
		priority 15
		signal fixPriOn
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 61)
			(narrator x: 100 y: 125)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance tom of Prop
	(properties
		x 134
		y 183
		noun N_TOM
		view vEndGameDevs
		cel 13
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 57)
			(narrator x: 40 y: 112)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance bobMallory of Prop
	(properties
		x 109
		y 181
		noun N_BOBMALLORY
		view vEndGameDevs
		loop 2
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 53)
			(narrator x: 20 y: 104)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance bobFischbach of Prop
	(properties
		x 252
		y 158
		noun N_BOBFISCHBACH
		view vEndGameDevs
		cel 15
		priority 10
		signal fixPriOn
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 49)
			(narrator x: 140 y: 83)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance jonBock of Prop
	(properties
		x 270
		y 139
		noun N_JONBOCK
		view vEndGameDevs
		cel 3
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 45)
			(narrator x: 190 y: 64)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance jerryMoore of Prop
	(properties
		x 304
		y 137
		noun N_JERRYMOORE
		view vEndGameDevs
		cel 2
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 41)
			(narrator x: 215 y: 59)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance gloria of Prop
	(properties
		x 284
		y 123
		noun N_GLORIA
		view vEndGameDevs
		cel 5
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 37)
			(narrator x: 177 y: 50)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance vana of Prop
	(properties
		x 299
		y 119
		noun N_VANA
		view vEndGameDevs
		cel 14
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 33)
			(narrator x: 216 y: 47)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance wilWong of Prop
	(properties
		x 269
		y 46
		noun N_WILWONG
		view vEndGameDevs
		cel 1
		priority 4
		signal fixPriOn
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 29)
			(narrator x: 153 y: 30)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance diana of Prop
	(properties
		x 295
		y 54
		noun N_DIANA
		view vEndGameDevs
		cel 7
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 25)
			(narrator x: 169 y: 0)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance jeffCrowe of Prop
	(properties
		x 241
		y 77
		noun N_JEFFCROWE
		view vEndGameDevs
		cel 4
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 21)
			(narrator x: 137 y: 0)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance ericKasner of Prop
	(properties
		x 190
		y 39
		noun N_ERICKASNER
		view vEndGameDevs
		cel 6
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 17)
			(narrator x: 75 y: 0)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance arturo of Prop
	(properties
		x 102
		y 104
		noun N_ARTURO
		view vEndGameDevs
		priority 4
		signal fixPriOn
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 1)
			(narrator x: 22 y: 26)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance richardP of Prop
	(properties
		x 24
		y 51
		noun N_RICHARDP
		view vEndGameDevs
		cel 11
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 13)
			(narrator x: 22 y: 10)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance rogerHardy of Prop
	(properties
		x 158
		y 129
		noun N_ROGERHARDY
		view vEndGameDevs
		cel 10
		priority 4
		signal fixPriOn
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 9)
			(narrator x: 63 y: 57)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance richardA of Prop
	(properties
		x 288
		y 157
		noun N_RICHARDA
		view vEndGameDevs
		cel 12
		signal ignrAct
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(if (== theVerb V_LOOK)
			(clickOnPeople start: 5)
			(narrator x: 158 y: 79)
			(self setScript: clickOnPeople)
		)
		(super doVerb: theVerb)
	)
)

(instance awardScript of Script
	(method (init)
		(if (Btst fSavedBarnard)
			(baronette addToPic:)
		)
		(elsa addToPic:)
		(minotaur cel: (if (Btst fMinotaurDead) 0 else 1))
		(sheriffAndOtto addToPic:)
		(karl addToPic:)
		(kattaAndAbdul addToPic:)
		(thiefAndCrusher addToPic:)
		(centaurs addToPic:)
		(swordmaster addToPic:)
		(erasmusAndFenrus addToPic:)
		(shopkeeperAndZara addToPic:)
		(hermitAndHealer addToPic:)
		(grannyAndCat addToPic:)
		(guildmaster addToPic:)
		(bartender addToPic:)
		(minotaur addToPic:)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(yoric hide:)
				(= seconds 10)
			)
			(1
				(= seconds 3)
			)
			(2
				(theGame setCursor: waitCursor TRUE)
				(User canInput: FALSE)
				(HandsOff)
				(yoric
					show:
					illegalBits: 0
					setLoop: 5
					setCycle: Forward
					cycleSpeed: 9
					setMotion: MoveTo 130 153 self
				)
			)
			(3
				(yoric setLoop: 5 setCel: 0)
				(= cycles 3)
			)
			(4
				(yoric setCel: 0 stopUpd:)
				(baron cycleSpeed: 11 setCycle: EndLoop self)
				(narrator x: 60 y: 137)
			)
			(5
				(ourHero cycleSpeed: 9 setCycle: EndLoop self)
				(baron addToPic:)
				(messager say: N_ROOM V_ALTTALK)
			)
			(6
				(narrator x: -1 y: -1)
				(curRoom newRoom: ENDGAME2)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(== state 0)
					(or
						(== (event type?) mouseDown)
						(== (event type?) keyDown)
					)
				)
				(= seconds 10)
			)
			((and (== state 0) (== seconds 0))
				(HandsOff)
				(self cue:)
			)
		)
	)
)

(instance clickOnPeople of Script
	(method (init)
		(User canInput: FALSE)
		(theGame setCursor: waitCursor TRUE)
		(super init: &rest)
	)
	
	(method (dispose)
		(User canInput: TRUE)
		(theGame setCursor: 941 TRUE)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				1
				(messager say: N_ARTURO V_LOOK)
				(= seconds 1)
			)
			(1
				2
				(arturo forceUpd: loop: 1 cel: 0)
				(= seconds 2)
			)
			(2
				(arturo forceUpd: y: 0 cel: 0)
				(= cycles 1)
			)
			(3 4
				(self dispose:)
			)
			(4
				5
				(messager say: N_RICHARDA V_LOOK)
				(= seconds 1)
			)
			(5
				6
				(richardA forceUpd: loop: 1 cel: 12)
				(= seconds 2)
			)
			(6
				7
				(richardA forceUpd: loop: 0 cel: 12)
				(= cycles 1)
			)
			(7
				8
				(self dispose:)
			)
			(8
				9
				(messager say: N_ROGERHARDY V_LOOK)
				(= seconds 1)
			)
			(9
				(rogerHardy forceUpd: loop: 1 cel: 10)
				(= seconds 2)
			)
			(10
				11
				(rogerHardy forceUpd: loop: 0 cel: 10)
				(= cycles 1)
			)
			(11
				12
				(self dispose:)
			)
			(12
				13
				(messager say: N_RICHARDP V_LOOK)
				(= seconds 1)
			)
			(13
				14
				(richardP forceUpd: loop: 1 cel: 11)
				(= seconds 2)
			)
			(14
				15
				(richardP forceUpd: loop: 0 cel: 11)
				(= cycles 1)
			)
			(15 16 (self dispose:))
			(16
				17
				(messager say: N_ERICKASNER V_LOOK)
				(= seconds 1)
			)
			(17
				18
				(ericKasner forceUpd: loop: 1 cel: 6)
				(= seconds 2)
			)
			(18
				19
				(ericKasner forceUpd: loop: 0 cel: 6)
				(= cycles 1)
			)
			(19 20 (self dispose:))
			(20
				21
				(messager say: N_JEFFCROWE V_LOOK)
				(= seconds 1)
			)
			(21
				22
				(jeffCrowe forceUpd: loop: 1 cel: 4)
				(= seconds 2)
			)
			(22
				23
				(jeffCrowe forceUpd: loop: 0 cel: 4)
				(= cycles 1)
			)
			(23 24 (self dispose:))
			(24
				25
				(messager say: N_DIANA V_LOOK)
				(= seconds 1)
			)
			(25
				26
				(diana forceUpd: loop: 1 cel: 7)
				(= seconds 2)
			)
			(26
				27
				(diana forceUpd: loop: 0 cel: 7)
				(= cycles 1)
			)
			(27 28 (self dispose:))
			(28
				29
				(messager say: N_WILWONG V_LOOK)
				(= seconds 1)
			)
			(29
				30
				(wilWong forceUpd: loop: 1 cel: 1)
				(= seconds 2)
			)
			(30
				31
				(wilWong forceUpd: loop: 0 cel: 1)
				(= cycles 1)
			)
			(31 32 (self dispose:))
			(32
				33
				(messager say: N_VANA V_LOOK)
				(= seconds 1)
			)
			(33
				34
				(vana forceUpd: loop: 1 cel: 14)
				(= seconds 2)
			)
			(34
				35
				(vana forceUpd: loop: 0 cel: 14)
				(= cycles 1)
			)
			(35 36 (self dispose:))
			(36
				37
				(messager say: N_GLORIA V_LOOK)
				(= seconds 1)
			)
			(37
				38
				(gloria forceUpd: loop: 1 cel: 5)
				(= seconds 2)
			)
			(38
				39
				(gloria forceUpd: loop: 0 cel: 5)
				(= cycles 1)
			)
			(39 40 (self dispose:))
			(40
				41
				(messager say: N_JERRYMOORE V_LOOK)
				(= seconds 1)
			)
			(41
				42
				(jerryMoore forceUpd: loop: 1 cel: 2)
				(= seconds 2)
			)
			(42
				43
				(jerryMoore forceUpd: loop: 0 cel: 2)
				(= cycles 1)
			)
			(43 44 (self dispose:))
			(44
				45
				(messager say: N_JONBOCK V_LOOK)
				(= seconds 1)
			)
			(45
				46
				(jonBock forceUpd: loop: 1 cel: 3)
				(= seconds 2)
			)
			(46
				47
				(jonBock forceUpd: loop: 0 cel: 3)
				(= cycles 1)
			)
			(47 48 (self dispose:))
			(48
				49
				(messager say: N_BOBFISCHBACH V_LOOK)
				(= seconds 1)
			)
			(49
				50
				(bobFischbach forceUpd: loop: 1 cel: 15)
				(= seconds 2)
			)
			(50
				51
				(bobFischbach forceUpd: loop: 0 cel: 15)
				(= cycles 1)
			)
			(51 52 (self dispose:))
			(52
				53
				(messager say: N_BOBMALLORY V_LOOK)
				(= seconds 1)
			)
			(53
				54
				(bobMallory forceUpd: loop: 3 cel: 15)
				(= seconds 2)
			)
			(54
				55
				(bobMallory forceUpd: loop: 2 cel: 15)
				(= cycles 1)
			)
			(55 56 (self dispose:))
			(56
				57
				(messager say: N_TOM V_LOOK)
				(= seconds 1)
			)
			(57
				58
				(tom forceUpd: loop: 1 cel: 13)
				(= seconds 2)
			)
			(58
				59
				(tom forceUpd: loop: 0 cel: 13)
				(= cycles 1)
			)
			(59 60 (self dispose:))
			(60
				61
				(messager say: N_OLIVER V_LOOK)
				(= seconds 1)
			)
			(61
				62
				(oliver forceUpd: loop: 3 cel: 2)
				(= seconds 2)
			)
			(62
				63
				(oliver forceUpd: loop: 2 cel: 2)
				(= cycles 1)
			)
			(63 64 (self dispose:))
			(64
				65
				(messager say: N_COREYCOLE V_LOOK)
				(= seconds 1)
			)
			(65
				66
				(coreyCole forceUpd: loop: 1 cel: 9)
				(= seconds 2)
			)
			(66
				67
				(coreyCole forceUpd: loop: 0 cel: 9)
				(= cycles 1)
			)
			(67 68 (self dispose:))
			(68
				69
				(messager say: N_LORICOLE V_LOOK)
				(= seconds 1)
			)
			(69
				70
				(loriCole forceUpd: loop: 1 cel: 8)
				(= seconds 2)
			)
			(70
				71
				(loriCole forceUpd: loop: 0 cel: 8)
				(= cycles 1)
			)
			(71 72 (self dispose:))
			(72
				73
				(messager say: N_CARLOS V_LOOK)
				(= seconds 1)
			)
			(73
				74
				(carlos forceUpd: loop: 3 cel: 1)
				(= seconds 2)
			)
			(74
				75
				(carlos forceUpd: loop: 2 cel: 1)
				(= cycles 1)
			)
			(75 76 (self dispose:))
		)
	)
)
