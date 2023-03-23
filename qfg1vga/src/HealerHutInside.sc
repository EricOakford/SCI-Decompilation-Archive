;;; Sierra Script 1.0 - (do not remove this comment)
(script# 55)
(include game.sh) (include "55.shm")
(use Main)
(use Teller)
(use Ware)
(use Procs)
(use Print)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm55 0
	healerTalker 1
)
(enum 1
	cueRing
	cueSellFlask
	cueStealPotions
	cueStamina
	cueHealing
	cueMana
	cueGhostOil
)

(enum -1
	noFunds
	buyNothing
	buyStamina
	buyHealing
	buyMana
	buyGhostOil
)

(local
	[local0 2]	;unused
	healerFacingAway
	local3
	kissCued
	theCycles
	local6	;unused
	lookedAtBarrel
	gaveCheetaurClaws
	healerCue
	healerTellMainBranch = [
		STARTTELL
		-25		;C_HEALER
		-34		;C_POTIONS
		-39		;C_SPELLCOMPONENTS
		-13		;C_CASTLE
		-12		;C_BRIGANDS
		ENDTELL
		]
	healerTell1 = [
		STARTTELL
		-33		;C_PET
		-37		;C_RING
		ENDTELL
		]
	healerTell2 = [
		STARTTELL
		C_HEALINGPOTION
		-43		;C_VIGORPOTION
		C_POWERPOTION
		-42		;C_UNDEADUNGUENT
		-16		;C_DISPELPOTION
		-29		;C_MAGIC
		ENDTELL
		]
	healerTell3 = [
		STARTTELL
		-11		;C_TROLLBEARD
		-21		;C_FLOWERS
		-14		;C_CHEETAURCLAWS
		C_FLASKS
		C_MANDRAKEROOT
		ENDTELL
		]
	healerTell4 = [
		STARTTELL
		C_BARON
		C_SON
		C_DAUGHTER
		ENDTELL
		]
	healerTell5 = [
		STARTTELL
		C_FARMER
		C_LEADER
		ENDTELL
		]
	healerTell6 = [
		STARTTELL
		C_PTEROSAUR
		ENDTELL
		]
	healerTell7 = [
		STARTTELL
		C_REWARD
		ENDTELL
		]
	healerTell8 = [
		STARTTELL
		C_STAMINA
		ENDTELL
		]
	healerTell9 = [
		STARTTELL
		C_ZOMBIES
		C_FLOATINGSPIRITS
		ENDTELL
		]
	healerTell10 = [
		STARTTELL
		C_DRYAD
		C_FLYINGWATER
		C_MAGICACORN
		C_GREENFUR
		C_MUSHROOMS
		ENDTELL
		]
	healerTell11 = [
		STARTTELL
		C_FAIRYDUST
		C_FAIRYRING
		ENDTELL
		]
	healerTell12 = [
		STARTTELL
		C_ERANASPEACE
		ENDTELL
		]
	[healerTellTree 18]
	healerTellKeys = [
		STARTTELL
		-25		;C_HEALER
		-34		;C_POTIONS
		-39		;C_SPELLCOMPONENTS
		-13		;C_CASTLE
		-12		;C_BRIGANDS
		-33		;C_PET
		-37		;C_RING
		-43		;C_VIGORPOTION
		-42		;C_UNDEADUNGUENT
		-16		;C_DISPELPOTION
		-29		;C_MAGIC
		-21		;C_FLOWERS
		ENDTELL
		]
)
(procedure (DispelIngredients &tmp [str 40] [dustBuf 11] [furBuf 11] [flowerBuf 20] [acornBuf 11] [waterBuf 11] [buffer 200])
	(if (and (not (Btst fHealerMakingDispel)) (not (Btst fGotDispel)))
		(cond 
			(
				(and
					(Btst fGaveDust)
					(Btst fGaveFur)
					(Btst fGaveFlowers)
					(Btst fGaveAcorn)
					(Btst fGaveWater)
				)
				(curRoom setScript: waitForHealer)
				(Bset fHealerMakingDispel)
				(Bclr fGotDispel)
				(Bclr fGaveDust)
				(Bclr fGaveFur)
				(Bclr fGaveFlowers)
				(Bclr fGaveAcorn)
				(Bclr fGaveWater)
			)
			((Btst fHealerKnowsDispel)
				(Message MsgGet 55 N_DISPELRECIPE NULL C_HEALERNEEDS 1 @str)
				(Message MsgGet 55 N_DISPELRECIPE NULL C_NEED_FAIRYDUST 1 @dustBuf)
				(Message MsgGet 55 N_DISPELRECIPE NULL C_NEED_GREENFUR 1 @furBuf)
				(Message MsgGet 55 N_DISPELRECIPE NULL C_NEED_FLOWERS 1 @flowerBuf)
				(Message MsgGet 55 N_DISPELRECIPE NULL C_NEED_ACORN 1 @acornBuf)
				(Message MsgGet 55 N_DISPELRECIPE NULL C_NEED_FLYINGWATER 1 @waterBuf)
				(Print
					addTextF:
						@buffer
						@str
						(if (Btst fGaveDust) {} else @dustBuf)
						(if (Btst fGaveFur) {} else @furBuf)
						(if (Btst fGaveFlowers) {} else @flowerBuf)
						(if (Btst fGaveWater) {} else @waterBuf)
						(if (Btst fGaveAcorn) {} else @acornBuf)
					init:
				)
			)
		)
	)
)

(instance rm55 of Room
	(properties
		noun N_ROOM
		picture 55
		style DISSOLVE
	)
	
	(method (init)
		(= [healerTellTree 0] @healerTellMainBranch)
		(= [healerTellTree 1] @healerTell1)
		(= [healerTellTree 2] @healerTell2)
		(= [healerTellTree 3] @healerTell3)
		(= [healerTellTree 4] @healerTell4)
		(= [healerTellTree 5] @healerTell5)
		(= [healerTellTree 6] @healerTell6)
		(= [healerTellTree 7] @healerTell7)
		(= [healerTellTree 8] @healerTell8)
		(= [healerTellTree 9] @healerTell9)
		(= [healerTellTree 10] @healerTell10)
		(= [healerTellTree 11] @healerTell11)
		(= [healerTellTree 12] @healerTell12)
		(= [healerTellTree 13] ENDTELL)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						319 133
						179 122
						270 143
						234 168
						115 152
						75 164
						0 140
						0 189
					yourself:
				)
		)
		(LoadMany RES_VIEW 57 56 523 525)
		(Load RES_SOUND 42)
		(super init:)
		(= numFlowers 0)
		(self
			setFeatures:
				pots
				barrel
				cheeseCloth
				bed
				woodTable
				litterBox
				healerWindow
				ladder
				ingredients
				onThings
		)
		;UPGRADE
;;;		(pots
;;;			init:
;;;			setOnMeCheck: ftrControl cGREEN
;;;		)
;;;		(barrel init:)
;;;		(cheeseCloth init:)
;;;		(bed init:)
;;;		(woodTable init:)
;;;		(litterBox init:)
;;;		(healerWindow init:)
;;;		(ladder init:)
;;;		(ingredients init:)
;;;		(onThings init:)
		
		(stoneTable
			init:
;;;			setOnMeCheck: ftrControl cBLUE
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
		)
		(fire init: setCycle: Forward)
		(kettle init: setCycle: Forward)
		(bottles
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
		)
		(NormalEgo)
		(bird init: setScript: preening)
		(ego init: posn: 137 243)
		(healerTeller init: healer @healerTellMainBranch @healerTellTree @healerTellKeys)
		(theMusic number: 122 loop: -1 init: play:)
		(curRoom setScript: egoEnters)
	)
	
	(method (doit)
		(cond 
			(script)
			(
				(or
					(== (ego edgeHit?) SOUTH)
					(< (ego x?) 3)
					(> (ego x?) 318)
				)
				(HandsOff)
				(curRoom setScript: sExitSouth)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(theMusic fade:)
		(Bset fBeenIn55)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(V_SLEEP (messager say: N_ROOM V_SLEEP))
			(V_LOOK (messager say: N_ROOM V_LOOK))
			(V_DETECT (messager say: N_ROOM V_DETECT))
			(V_FLAME (messager say: N_ROOM V_FLAME))
			(V_DAZZLE (messager say: N_ROOM V_FLAME))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance healerWin of SysWindow
	(properties
		color 1
	)
	
	(method (open &tmp temp0)
		(+= top (= temp0 (- 188 bottom)))
		(+= bottom temp0)
		(super open:)
	)
)

(instance cheeseCloth of Feature
	(properties
		x 19
		y 128
		noun N_CHEESECLOTH
		nsTop 123
		nsBottom 133
		nsRight 38
		sightAngle 40
	)
)

(instance bed of Feature
	(properties
		x 156
		y 104
		noun N_BED
		nsTop 89
		nsLeft 112
		nsBottom 119
		nsRight 200
		sightAngle 40
	)
)

(instance litterBox of Feature
	(properties
		x 121
		y 117
		z 65
		noun N_LITTERBOX
		nsTop 44
		nsLeft 105
		nsBottom 61
		nsRight 138
		sightAngle 40
	)
)

(instance healerWindow of Feature
	(properties
		x 130
		y 77
		noun N_HEALERWINDOW
		nsTop 70
		nsLeft 120
		nsBottom 84
		nsRight 141
		sightAngle 40
	)
)

(instance ingredients of Feature
	(properties
		x 249
		y 83
		noun N_INGREDIENTS
		nsTop 78
		nsLeft 215
		nsBottom 89
		nsRight 284
		sightAngle 40
	)
)

(instance woodTable of Feature
	(properties
		x 81
		y 147
		noun N_WOODTABLE
		nsTop 124
		nsLeft 39
		nsBottom 147
		nsRight 123
		sightAngle 40
	)
)

(instance ladder of Feature
	(properties
		x 70
		y 92
		noun N_LADDER
		nsTop 55
		nsLeft 54
		nsBottom 129
		nsRight 86
		sightAngle 40
	)
)

(instance stoneTable of Feature
	(properties
		x 195
		y 159
		noun N_STONETABLE
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_STONETABLE V_LOOK)
			)
			(V_DO
				(cond 
					((Btst fStolePotions)
						(messager say: N_STONETABLE V_DO C_GREEDY)
					)
					(healerFacingAway
						(messager say: N_STONETABLE V_DO C_FILCH)
						(bottles hide:)
						(Bset fStolePotions)
						(= healerCue cueStealPotions)
						(ego setScript: cueItScript)
					)
					(else
						(messager say: N_STONETABLE V_DO C_HEALERSEES)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pots of Feature
	(properties
		x 219
		y 135
		noun N_POTS
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_POTS V_LOOK)
			)
			(V_DO
				(messager say: N_POTS V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onThings of Feature
	(properties
		x 18
		y 144
		z 50
		noun N_THINGS
		nsTop 73
		nsBottom 116
		nsRight 36
	)
)

(instance barrel of Feature
	(properties
		x 7
		y 131
		z 93
		noun N_BARREL
		nsTop 24
		nsBottom 53
		nsRight 15
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(if (not lookedAtBarrel)
				(messager say: N_BARREL V_LOOK C_LOOKBARREL1)
				(= lookedAtBarrel TRUE)
			else
				(messager say: N_BARREL V_LOOK C_LOOKBARREL2)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance bottles of View
	(properties
		x 145
		y 135
		noun N_STONETABLE
		approachX 144
		approachY 162
		view 56
		loop 1
		priority 11
		signal (| stopUpdOn fixPriOn)
	)
	
	(method (doVerb theVerb)
		(stoneTable doVerb: theVerb &rest)
	)
)

(instance kettle of Prop
	(properties
		x 203
		y 120
		noun N_KETTLE
		nsTop 111
		nsLeft 190
		nsBottom 126
		nsRight 207
		view 56
		loop 7
		priority 9
		signal fixPriOn
		cycleSpeed 20
	)
)

(instance fire of Prop
	(properties
		x 199
		y 146
		noun N_FIRE
		nsTop 111
		nsLeft 190
		nsBottom 146
		nsRight 199
		view 56
		priority 10
		signal fixPriOn
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(pots doVerb: theVerb &rest)
	)
)

(instance bird of Prop
	(properties
		x 45
		y 52
		noun N_BIRD
		nsTop 73
		nsLeft 65
		nsBottom 82
		nsRight 87
		view 56
		loop 2
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_ROCK)
			(messager say: N_HEALER V_DAGGER)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance healer of Actor
	(properties
		noun N_HEALER
		approachX 105
		approachY 157
		view 57
		loop 2
		illegalBits $0000
	)
	
	(method (doit)
		(if (and kissCued (!= script healerKisses))
			(= kissCued FALSE)
			(healer setScript: healerKisses)
		)
		(super doit:)
	)
)

(instance healerTeller of Teller
	(method (showDialog)
		(super
			showDialog:
				-37
				(if
					(or (Btst fBeenIn311) (Btst fBeenIn55)) (not (Btst fReturnedRing))
				else
					0
				)
				-16 (ego has: iAcorn)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-14 (Bset fClawsKnown))
				(-11 (Bset fBeardKnown))
				(-12
					(if (or (Btst fBeenIn320) (Btst fBeenIn53))
						(super doChild: query)
					else
						(return TRUE)
					)
				)
				(-29
					(if (or (Btst fBeenIn70) (Btst fBeenIn76))
						(super doChild: query)
					else
						(return TRUE)
					)
				)
				(else  (super doChild: query))
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_HEALER V_DO NULL 1)
			)
			(V_DO
				(if (Btst fReturnedRing)
					(messager say: N_HEALER V_DO C_RING 2)
				else
					(messager say: N_HEALER V_DO C_RING 1)
				)
			)
			(V_TALK
				(SolvePuzzle f55TalkToHealer 2)
				(super doVerb: theVerb &rest)
			)
			(V_MONEY
				((= wareList (List new:))
					add:
						((Clone Ware) name: {Stamina} price: {20})
						((Clone Ware) name: {Healing} price: {40})
						((Clone Ware) name: {Mana} price: {60})
						((Clone Ware) name: {Undead} price: {100})
				)
				(switch ((ScriptID 551 0) doit:)
					(noFunds
						(messager say: N_HEALER V_MONEY C_NOFUNDS)
					)
					(buyStamina
						(messager say: N_HEALER V_MONEY C_BUY_POTION)
						(= healerCue cueStamina)
						(ego setScript: cueItScript)
					)
					(buyHealing
						(messager say: N_HEALER V_MONEY C_BUY_POTION)
						(= healerCue cueHealing)
						(ego setScript: cueItScript)
					)
					(buyMana
						(messager say: N_HEALER V_MONEY C_BUY_POTION)
						(= healerCue cueMana)
						(ego setScript: cueItScript)
					)
					(buyGhostOil
						(messager say: N_HEALER V_MONEY C_BUY_POTION)
						(= healerCue cueGhostOil)
						(ego setScript: cueItScript)
					)
				)
			)
			(V_RING
				(Bset fReturnedRing)
				(SolvePuzzle f55ReturnRing 10)
				(messager say: N_HEALER V_RING C_RING)
				((inventory at: iGold)
					amount: (+ ((inventory at: iGold) amount?) 6)
				)
				(= healerCue cueRing)
				(ego setScript: cueItScript)
				(= kissCued TRUE)
				(if (<= (+ (* (ego y?) 3) (ego x?) -615) 0)
					(= theCycles 20)
					(healer setScript: healerPleased)
				)
			)
			(V_FLOWERS
				(if (> numFlowers 2)
					(messager say: N_HEALER V_FLOWERS C_NOMORE)
				else
					(ego setScript: flowersScript)
				)
			)
			(V_GREENFUR
				(ego setScript: giveGreenFur)
			)
			(V_ACORN
				(ego setScript: giveAcorn)
			)
			(V_SEED
				(messager say: N_HEALER V_SEED C_NONEEDSEED)
			)
			(V_MUSHROOM
				(ego setScript: giveMushroom)
			)
			(V_CHEETAURCLAW
				(ego setScript: giveClaws)
			)
			(V_TROLLBEARD
				(ego setScript: giveBeard)
			)
			(V_FAIRYDUST
				(ego setScript: giveDust)
			)
			(V_WATER
				(cond 
					((not (ego has: iFlyingWater))
						(DontHave)
					)
					(numWater
						(messager say: N_HEALER V_WATER C_DISPELPOTION)
					)
					((not (Btst fHealerKnowsDispel))
						(messager say: N_HEALER V_WATER C_NONEEDWATER)
					)
					((not (Btst fHaveFlyingWater))
						(messager say: N_HEALER V_WATER C_GIVELAKEWATER)
					)
					(else
						(ego setScript: giveWater)
					)
				)
			)
			(V_ROCK
				(messager say: N_HEALER V_DAGGER)
			)
			(V_FLASK
				(= theCycles 6)
				(healer setScript: healerPleased)
				(messager say: N_HEALER V_FLASK)
				(= healerCue cueSellFlask)
				(ego setScript: cueItScript)
			)
			(V_DAGGER
				(messager say: N_HEALER V_DAGGER)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance egoEnters of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 185 self)
				(healer
					loop: 9
					init:
					posn: 151 142
					approachVerbs:
						V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
						V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
						V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
						V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
						V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
						V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
						V_VASE V_VEGETABLES V_TALK
					setCycle: Forward
				)
			)
			(1
				(= cycles 2)
			)
			(2
				(cond 
					((not (Btst fMetHealer))
						(messager say: N_ROOM NULL C_FIRSTENTRY 1 self)
					)
					(
						(and
							(not (Btst fHealerMakingDispel))
							(or (not (Btst fLearnedDispel)) (Btst fHealerKnowsDispel))
						)
						(messager say: N_ROOM NULL C_RETURNED 1 self)
					)
					(else
						(self cue:)
					)
				)
			)
			(3
				(if (not (Btst fMetHealer))
					(messager say: N_ROOM NULL C_FIRSTMEET 0 self)
				else
					(self cue:)
				)
			)
			(4
				(if (and (Btst fLearnedDispel) (ego has: iAcorn) (not (Btst fHealerKnowsDispel)))
					(messager say: N_ROOM NULL C_METDRYAD 1 self)
				else
					(self cue:)
				)
			)
			(5
				(if (and (Btst fLearnedDispel) (ego has: iAcorn) (not (Btst fHealerKnowsDispel)))
					(messager say: N_HEALER V_ACORN NULL 1 self)
					(Bset fHealerKnowsDispel)
				else
					(self cue:)
				)
			)
			(6
				(if (Btst fHealerMakingDispel)
					(curRoom setScript: giveDispel)
				else
					(self cue:)
				)
			)
			(7
				(healer setMotion: MoveTo 92 139 self)
			)
			(8
				(healer setLoop: 5 setCycle: EndLoop self)
			)
			(9
				(healer
					setLoop: 5
					setCycle: Forward
					setScript: healerPuttering
				)
				(HandsOn)
				(curRoom setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance preening of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(lizSound play: 50)
				(bird cel: 2 setCycle: EndLoop self)
			)
			(1
				;changed to fix speed bug
				(= ticks (* 2 (Random 9 27)))
			)
			(2
				(lizSound play: 50)
				(bird setCycle: BegLoop self)
			)
			(3
				;changed to fix speed bug
				(= ticks (* 2 (Random 100 200)))
			)
			(4 (self changeState: 0))
		)
	)
)

(instance healerPuttering of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				;changed to fix speed bug
				(= ticks (* 2 (Random 90 200)))
			)
			(1
				(healer setLoop: 4 cel: 2 setCycle: BegLoop self)
			)
			(2
				(= healerFacingAway TRUE)
				(healer
					loop: 1
					setCycle: Walk
					setMotion: MoveTo 87 138 self
				)
			)
			(3
				(healer loop: 3)
				(= seconds 3)
			)
			(4
				(= healerFacingAway FALSE)
				(healer
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 151 142 self
				)
			)
			(5
				(healer
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 90 139 self
				)
			)
			(6
				(healer setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(7
				(healer setLoop: 5 cel: 0 setCycle: Forward)
				(self changeState: 0)
			)
		)
	)
)

(instance healerPleased of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(healer setMotion: 0 loop: 2)
				(= cycles 2)
			)
			(1
				(healer setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			;changed to address speed bug
			(2
				(= ticks (* 2 theCycles))
			)
			(3
				(switch local3
					(0
						(healer
							setLoop: -1
							setCycle: Walk
							setMotion: MoveTo 164 130 self
						)
					)
					(1
						(healer
							setLoop: -1
							setCycle: Walk
							setMotion: MoveTo 164 130 self
						)
					)
				)
			)
			(4
				(healer setLoop: 5 setCycle: EndLoop self)
			)
			(5
				(healer
					setLoop: 5
					setCycle: Forward
					setScript: healerPuttering
				)
			)
		)
	)
)

(instance healerKisses of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				
				(HandsOff)
				(healer setLoop: 6 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(if
					(not
						(if
							(and
								(<= 138 (healer x?))
								(<= (healer x?) 140)
								(<= 138 (healer y?))
							)
							(<= (healer y?) 140)
						)
					)
					(switch local3
						(0
							(healer
								setLoop: -1
								setCycle: Walk
								illegalBits: 0
								setMotion: MoveTo 139 139 self
							)
						)
						(1
							(healer
								setLoop: 2
								setCycle: Walk
								setMotion: MoveTo 139 139 self
							)
						)
					)
				else
					(= cycles 2)
				)
			)
			(2
				(healer setLoop: 2 setMotion: MoveTo 121 152)
				(ego illegalBits: 0 setMotion: MoveTo 146 154 self)
			)
			(3
				(ego setLoop: 1 ignoreActors:)
				(= cycles 2)
			)
			(4
				(healer
					setLoop: 7
					cel: 0
					ignoreActors:
					setCycle: CycleTo 2 1 self
				)
			)
			(5
				(ego hide:)
				(healer
					setLoop: 7
					posn: (+ (healer x?) 2) (+ (healer y?) 5)
					setCycle: CycleTo 6 1 self
				)
			)
			(6 (kiss init: play: self))
			(7
				(healer setLoop: 7 setCycle: CycleTo 3 -1 self)
			)
			(8
				(ego show:)
				(healer setLoop: 7 setCycle: CycleTo 0 -1 self)
			)
			(9
				(ego
					view: 525
					loop: 2
					cel: 0
					posn: (+ (ego x?) 5) (+ (ego y?) 3)
					setPri: 11
					setCycle: Forward
					setMotion: MoveTo 137 175 self
				)
			)
			(10
				(messager say: N_ROOM 0 C_REWARDLEAVE 1 self)
			)
			(11
				(healer
					setLoop: -1
					setCycle: Walk
					setPri: 12
					moveSpeed: 12
					setMotion: MoveTo 135 210 self
				)
				(NormalEgo)
				(ego
					view: 0
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 155 210 self
				)
			)
			(12 (self dispose:))
		)
	)
)

(instance waitForHealer of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(messager say: N_ROOM NULL C_COMEBACKLATER)
				(= seconds 3)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 243 self)
			)
			(1
				(if (and (not (Btst fMetHealer)) (not (Btst fReturnedRing)))
					(messager say: N_ROOM NULL C_TELLABOUTRING 1 self)
				else
					(self cue:)
				)
			)
			(2
				(Bset fMetHealer)
				(curRoom newRoom: 54)
			)
		)
	)
)

(instance flowersScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= theCycles 10)
				(messager say: N_HEALER V_FLOWERS C_GIVECOMPONENTS 1 self)
				;Trying to show the complete sequence fragments memory
				; if the Healer then lists the remaining
				; Dispel potion ingredients.
				;(messager say: N_HEALER V_FLOWERS C_GIVECOMPONENTS)
				;(self cue:)
			)
			(1
				(Bset fGaveFlowers)
				(++ numFlowers)
				(ego use: iFlowers 5)
				(SolvePuzzle f55SellFlowers 1)
				(DispelIngredients)
				(= seconds 2)
			)
			(2
				(HandsOn)
				(ego get: iSilver 5)
				(healer setScript: healerPleased)
			)
		)
	)
)

(instance giveGreenFur of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= theCycles 10)
				(ego use: iGreenFur)
				(Bset fGaveFur)
				(SolvePuzzle f55GiveFur 2)
				(healer setScript: healerPleased)
				(= ticks 60)
			)
			(1
				(if (Btst fHealerKnowsDispel)
					(messager say: N_HEALER V_GREENFUR C_DISPELPOTION 1 self)
				else
					(messager say: N_HEALER V_GREENFUR NULL 1 self)
				)
			)
			(2
				(DispelIngredients)
				(= seconds 2)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance giveAcorn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fHealerKnowsDispel)
				(= theCycles 10)
				(healer setScript: healerPleased)
				(messager say: N_HEALER V_ACORN NULL 1 self)
			)
			(1
				(Bset fGaveAcorn)
				(SolvePuzzle f55GiveAcorn 5)
				(DispelIngredients)
				(= seconds 2)
			)
			(2
				(HandsOn)
				(ego use: iAcorn)
				(self dispose:)
			)
		)
	)
)

(instance giveMushroom of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					;Changes have been made to fix the bug where you can sell mushrooms
					; after the healer has enough. This bug was fixed in the Macintosh version.
					((> numMushrooms 2)
						(messager say: N_HEALER V_MUSHROOM C_NOMORE 1 self)
						(self changeState: 2)
					)
					((Btst fHaveToadstools)
						(messager say: N_HEALER V_MUSHROOM C_GIVETOADSTOOLS 1 self)
					)
					(else
						(= theCycles 10)
						(healer setScript: healerPleased)
						;(messager say: N_HEALER V_MUSHROOM C_GIVECOMPONENTS 1 self)
						(messager say: N_HEALER V_MUSHROOM C_GIVECOMPONENTS)	;now shows complete sequence
						(self cue:)
						;(++ numMushrooms)	;moved to next state
					)
				)
			)
			(1
				(if (not (Btst fHaveToadstools))
					;increment mushrooms sold here				
					(++ numMushrooms)
					(ego get: iGold 1)
					(ego use: iMushroom 3)
				)
				(self cue:)
			)
			(2
				(HandsOn)
				(if (and (> numMushrooms 2) (not (Btst fHaveToadstools)))
					(SolvePuzzle f55SellMushroom 1)
				)
				(self dispose:)
			)
		)
	)
)


(instance giveClaws of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= theCycles 10)
				(healer setScript: healerPleased)
				(= ticks 60)
			)
			(1
				(if (== gaveCheetaurClaws TRUE)
					(self cue:)
				else
					(= gaveCheetaurClaws TRUE)
					(messager say: N_HEALER C_GIVECOMPONENTS 0 1 self)
				)
			)
			(2
				(messager say: N_HEALER C_GIVECOMPONENTS 0 2 self)
			)
			(3
				(SolvePuzzle f55SellClaws 2)
				(= ticks 30)
			)
			(4
				(HandsOn)
				(ego
					get: iSilver 5
					use: iCheetaurClaw
				)
				(self dispose:)
			)
		)
	)
)

(instance giveBeard of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= theCycles 10)
				(healer setScript: healerPleased)
				(= ticks 60)
			)
			(1
				(messager say: N_HEALER V_TROLLBEARD 0 0 self)
			)
			(2
				(ego use: iTrollBeard)
				(= ticks 60)
			)
			(3
				(HandsOn)
				(ego get: iHealingPotion)
				(ego get: iHealingPotion)
				(SolvePuzzle f55SellBeard 2)
				(self dispose:)
			)
		)
	)
)

(instance giveDust of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= theCycles 10)
				(healer setScript: healerPleased)
				(= ticks 30)
			)
			(1
				(if (and (not (Btst fHealerMakingDispel)) (Btst fHealerKnowsDispel))
					(messager say: N_HEALER V_FAIRYDUST C_DISPELPOTION 1 self)
				else
					(messager say: N_HEALER V_FAIRYDUST C_DISPELPOTION 2 self)
				)
			)
			(2
				(Bset fGaveDust)
				(SolvePuzzle f55GiveDust 2)
				(DispelIngredients)
				(= seconds 2)
			)
			(3
				(ego use: iFairyDust)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance giveWater of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_HEALER V_WATER C_GIVEFLYINGWATER 0 self)
			)
			(1
				(= theCycles 10)
				(healer setScript: healerPleased)
				(++ numWater)
				(Bset fGaveWater)
				(SolvePuzzle f55GiveWater 2)
				(DispelIngredients)
				(= ticks 60)
			)
			(2
				(ego use: iFlyingWater)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance giveDispel of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(healer setMotion: MoveTo 92 139 self)
			)
			(1
				(ego get: iDisenchant)
				(Bclr fHealerMakingDispel)
				(Bset fGotDispel)
				(SolvePuzzle f55GetDispel 7)
				(healer setLoop: 5 setCycle: EndLoop self)
			)
			(2
				(ego setMotion: PolyPath 105 157 self)
			)
			(3
				(messager say: N_ROOM NULL C_GETDISPEL) ;Now shows complete sequence
				(self cue:)
			)
			(4
				(healer
					setLoop: 5
					setCycle: Forward
					setScript: healerPuttering
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cueItScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(switch healerCue
					(cueRing
						(ego use: iRing)
						(ego get: iHealingPotion 2)
;						(ego get: iHealingPotion)
					)
					(cueSellFlask
						(ego use: iFlask)
						(ego get: iSilver 1)
					)
					(cueStealPotions (ego get: iHealingPotion 2))
					(cueStamina (ego get: iStaminaPotion))
					(cueHealing (ego get: iHealingPotion))
					(cueMana (ego get: iManaPotion))
					(cueGhostOil (ego get: iGhostOil))
				)
				(self cue:)
			)
			(2 (self dispose:))
		)
	)
)

(instance lizSound of Sound
	(properties
		number 74
		vol 45
	)
)

(instance kiss of Sound
	(properties
		number 42
		priority 5
	)
)

(instance healerTalker of Talker
	(properties
		x 10
		y 10
		view 1055
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2055)
		(PalVary PALVARYTARGET 2055)
		(AssertPalette 1055)
		(= font userFont)
		(super init: healerBust healerEye healerMouth &rest)
	)
)

(instance healerBust of Prop
	(properties
		view 1055
	)
)

(instance healerMouth of Prop
	(properties
		nsTop 52
		nsLeft 40
		view 1055
		loop 1
	)
)

(instance healerEye of Prop
	(properties
		nsTop 41
		nsLeft 39
		view 1055
		loop 2
	)
)
