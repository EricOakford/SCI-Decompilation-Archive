;;; Sierra Script 1.0 - (do not remove this comment)
(include system.sh) (include sci2.sh)
(include verbs.sh) (include talkers.sh)
(include "815.shm")

(define MAZEBUG 238)
(define STARTTELL 0)
(define ENDTELL	999)

; howFast values
(enum
	slow
	medium
	fast
	fastest
)

; General Game Defines
(define	NUM_STATS	5)
(define	NUM_SKILLS	8)
(define	NUM_DERIVS	4)
(define NUM_SPELLS	8)
(define NUM_ATTRIBS 25)	;(+ NUM_STATS NUM_SKILLS NUM_DERIVS NUM_SPELLS))
(define GAMEDAY 3600)	;number of ticks per game day
(define GAMEHOUR 150) 	;number of ticks per game hour
(define NUM_INVITEMS 40)
(define STAM_RATE	20)		;recovery rate for stamina
(define HEAL_RATE	15)		;recovery rate for health
(define MANA_RATE	5)		;recovery rate for mana

; Priority States
(define LowPri 4) ;pRED
(define HighPri 7)	;pLGREY

;Hero's Quest Framework scripts
(define MAIN			0)
(define GLORY_INIT		1)
(define NOTICE			2)
(define NOTICE2			9)
(define DRINK 			5)
(define EAT 			6)
(define SLEEP 			7)
(define REST			8)
(define SLEEPALLNIGHT	88)		;EO: This has number 88, which was previously a room removed from this version.
(define CASTFLAME		100)
(define CASTDAGGER		101)
(define CASTROCK		102)
(define GETROCK			103)
(define CASTCALM		104)
(define CASTOPEN		105)
(define CASTDAZZ		106)
(define EGOSEZ			120)	;doVerb on ego
(define PRAGFAIL		121)	;pragmaFail on ego
(define PAINREACTION	155)
(define INTRO			200)
(define CHARSEL			202)
(define CHALLOC			203)
(define CHARSHEET		204)
(define STATUSBAR		205)
(define GLORYINV		206)
(define SPEED			299)
(define TELLER			550)
(define WARE			551)
(define ABOUT			556)
(define ACTIONBAR		557)
(define SPELLS			558)
(define GLORY_WINDOW	559)
(define ENDGAME			600)
(define CHARSAVE 		601)
(define ENDGAME2		602)
(define LOGOROOM		603)
(define DOOR 			800)
(define TALKOBJ			803)
(define KEYCURSOR		810)
(define	TARGET			812)
(define TIME			813)
(define PROCS			814)
(define GLORY_PRINT		815)
(define GLORY_OBSTACLES 816)
(define STARTAROOM		896)

; Locales and Regions
(define DEBUG	 298) ;Locale
(define TOWN 	 801) ;Locale
(define FOREST 	 804) ;Locale
(define GHOSTS 	 805) ;Actors and Scripts for CEMETERY
(define CEMETERY 806) ;Region
(define CASTLE 	 807) ;Locale
(define STREET 	 811) ;Locale

; Door states
(enum
	doorClosed
	doorClosing
	doorOpen
	doorOpening
)

; Picture defines
(define pBlueSkyForCarpet 750)

;core arena regions, classes and scripts
(define ENCOUNTER 	210) ;Region
(define ARENA 		211) ;Region
(define SKILLED 	212) ;Class
(define WARRIOR 	213) ;Class
(define MONSTER 	214) ;Class
(define CLOSECOMBAT 215) ;Script


; Character Classes
(enum
	FIGHTER
	MAGIC_USER
	THIEF
)

; Ego Moving Modes
(enum
	MOVE_WALK		;0
	MOVE_RUN		;1
	MOVE_SNEAK		;2
)

; Skills
(enum
	STR			;0
	INT			;1
	AGIL		;2
	VIT			;3
	LUCK		;4
	WEAPON		;5
	PARRY		;6
	DODGE		;7
	STEALTH		;8
	PICK		;9
	THROW		;10
	CLIMB		;11
	MAGIC		;12
	EXPER		;13
	HEALTH		;14
	STAMINA		;15
	MANA		;16
; Magic Spells
	OPEN		;17
	DETMAGIC	;18
	TRIGGER		;19
	DAZZLE		;20
	ZAP			;21
	CALM		;22
	FLAMEDART	;23
	FETCH		;24
)
; ///////////////////////////////////////////////////////
; Inventory
; ///////////////////////////////////////////////////////
(enum
	iSilver			;0
	iRations		;1
	iSword			;2
	iChainmail		;3
	iLeather		;4
	iShield			;5
	iDagger			;6
	iLockPick		;7
	iThiefKit		;8
	iThiefLicense	;9
	iRock			;10
	iFlask			;11
	iHealingPotion	;12
	iManaPotion		;13
	iStaminaPotion	;14
	iDisenchant		;15
	iBrassKey		;16
	iMagicGem		;17
	iRing			;18
	iGhostOil		;19
	iMagicMirror	;20
	iMandrake		;21
	iFruit			;22
	iVegetables		;23
	iAcorn			;24
	iSeed			;25
	iFlowers		;26
	iGreenFur		;27
	iFairyDust		;28
	iFlyingWater 	;29
	iMushroom		;30
	iVase			;31
	iCandelabra		;32
	iMusicBox		;33
	iCandlesticks	;34
	iPearls			;35
	iCheetaurClaw	;36
	iTrollBeard		;37
	iGold			;38
	iPaper			;39
	iLastInvItem	;40	;this MUST be last
)

; Spells known (used for spell menu)
(define SPELL_OPEN		$0001)
(define SPELL_DETECT	$0002)
(define SPELL_TRIGGER	$0004)
(define SPELL_DAZZLE	$0008)
(define SPELL_ZAP		$0010)
(define SPELL_CALM		$0020)
(define SPELL_FLAMEDART	$0040)
(define SPELL_FETCH		$0080)

; Time Zones
(enum
	TIME_DAWN
	TIME_MIDMORNING
	TIME_MIDDAY
	TIME_MIDAFTERNOON
	TIME_SUNSET
	TIME_NIGHT
	TIME_MIDNIGHT
	TIME_NOTYETDAWN
)

;TrySkill and SkillUsed amounts
;Skill Used amounts
(define tryStatThrowing 10) ;full define should be (/ [egoStats AGIL] 10) but SCICompanion doesn't support those types of defines
(define tryStatWeaponMaster 50)
(define tryStatKoboldDazzle 5)
(define tryStatStableStr 25)
(define tryStatStableVit 40)
(define tryStatRandomEncounter 12) ;full define should be (/ [monsterHP (GetMonsterIndex monsterInRoom)] 12) but SCICompanion doesn't support those types of defines

;TrySkill thresholds
(define tryPickSecretEntrance 85)
(define tryForceSecretEntrance 40) ;strength
(define tryBreakDownBrigandGate 60) ;strength
(define tryClimbBrigandGate 65)
(define tryClimbIntoTown 35)
(define tryFetchKoboldKey 35)
(define tryForceOpenKoboldChest 40) ;strength
(define tryPickKoboldChest 70)
(define tryCastOpenKoboldChest 50) ;open spell
(define trySneak 5)
(define trySneakKobold 35)
(define tryPickNose 40)
(define tryCastOpenFox 25) ;open spell
(define tryClimbSpittingSpirea 35)
(define tryClimbWaterfall 30)
(define tryCastOpenHenry 45) ;magic, specifically.
(define tryThrowHenry 25)
(define tryFaeryDance 25) ;agility
(define tryThrowNest 25)
(define tryPickLOL 30)
(define tryPickSheriff 50)
(define tryWalkTreeLimb 30) ;agility (the tree outside the healer's)
(define tryPickTavern 10)

;Random Encounter Entrances
(define reNORTH 1)
(define reEAST	2)
(define reSOUTH 4)
(define reWEST	8)

;note, the other creatures have their maxHP in a array in RandomEncounters.sc
(define MAX_HP_OGRE 112)
(define MAX_HP_BRUTUS 100)
(define MAX_HP_KOBOLD 67)
(define MAX_HP_BEAR 133)


; Nest Condition
(enum
	nestInTree
	nestOnGround
	nestBurnt
)

; Bar Drinks
(enum
	drinkNOTHING
	drinkALE
	drinkSWEAT
	drinkBREATH
)

; Baba Yaga states
(enum
	babaNAME
	babaBRAVE
	babaFETCH
	babaBRING
	babaFINALE
)

; Baba Yaga's Hut states
(enum
	hutINITIAL
	hutMETSKULL
	hutNODEAL
	hutDEALMADE
	hutSTATE4
	hutGAVEGEM	; Now 5 instead of 4 like before
)

; Inn Meal States
(enum
	mealNOTHING
	mealORDERED
	mealATTABLE
	mealFINISHED
	mealCLEARED
)


; Mages Maze
(define ChaseRange 30)
	; Directions
(enum
	mazeN
	mazeNE
	mazeE
	mazeSE
	mazeS
	mazeSW
	mazeW
	mazeNW
)
; Bug Sizes
(enum
	smallBug
	mediumBug
	largeBug
)
; doCommands
(enum
	mmazeNOTHING
	mmazeCHOOSE
	mmazeFETCH
	mmazeOPEN
	mmazeTRIGGER
	mmazeFLAME
)

; Kobold States
(enum
	koboldASLEEP
	koboldAWAKE
	koboldSTATE2
	koboldSTATE3
	koboldDEAD
)

;Arena Actions (for the 'Skilled' class)
(enum
	aaNOTHING
	aaTHRUST
	aaSLASH
	aaPARRYUP
	aaPARRYDOWN
	aaDODGELEFT
	aaDODGERIGHT
	aaDUCK
	aaLEAP
	aaACTION_9
	aaDIE
	aaMAGIC
)

;Armor values
(define LEATHER_VALUE 2)
(define CHAIN_VALUE 5)

;Weapon values
(define DAGGER_VALUE 5)
(define SWORD_VALUE 8)

; Icons
(enum 
	ICON_LEFT	;left border of icon bar
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_ACTIONS
	ICON_CAST
	ICON_USEIT
	ICON_INVENTORY
	ICON_CONTROL
	ICON_HELP
	ICON_RIGHT	;right border of icon bar
)

; Action Bar Icons
(define ACTION_WALK		$0001)
(define ACTION_RUN		$0002)
(define ACTION_SNEAK	$0004)
(define ACTION_REST		$0008)

; Monster Arenas
(define KOBOLD 15)

;Views
(define vBear 420)
(define vMinotaur 425)
(define vSaurus 430)
(define vMantray 435)
(define vCheetaur 440)
(define vGoblin 445)
(define vTroll 450)
(define vOgre 455)
(define vDragon 460)	;actually Saurus Rex, but it is called a dragon internally
(define vBrigand 465)
(define vBrigandLeader 470) ;unused
(define vStatusBar 803)
(define vMonoStatusBar 804)	;unused in VGA

; Event Flags
; flags are assigned for every room after ego leaves it.
; if these aren't set, then ego has never been in the room before.
(enum
	fBeenIn10 			;0
	fBeenIn11 			;1
	fBeenIn12 			;2
	fBeenIn13 			;3
	fBeenIn14 			;4
	fBeenIn15			;5
	fBeenIn16			;6
	fBeenIn17			;7
	fBeenIn18 			;8
	fBeenIn19 			;9
	fBeenIn20			;10 ;unused (Room 20 does not exist)
	fBeenIn21			;11
	fBeenIn22			;12
	fBeenIn23			;13
	fBeenIn24			;14
	fBeenIn25			;15
	fBeenIn26			;16
	fBeenIn27			;17
	fBeenIn28 	;18
	fBeenIn29 	;19
	fBeenIn30 		;20
	fBeenIn31 		;21
	fBeenIn32 		;22 ;unused (Room 32 is the Wizard's Game)
	fBeenIn33 			;23
	fBeenIn34 			;24
	fBeenIn35 			;25
	fBeenIn36 			;26
	fBeenIn37 		;27
	fBeenIn38 	;28
	fBeenIn39 	;29 ;unused
	fBeenIn40 		;30 ;unused
	fBeenIn41 		;31
	fBeenIn42 			;32
	fBeenIn43 			;33
	fBeenIn44 			;34
	fBeenIn45 		;35
	fBeenIn46 			;36 ;unused (deleted Goblin Cave scene)
	fBeenIn47 			;37 ;unused (deleted Goblin Cave scene)
	fBeenIn48 			;38 ;unused (deleted Goblin Cave scene)
	fBeenIn49 			;39 ;unused (deleted Goblin Cave scene)
	fBeenIn50 			;40 ;unused (deleted Goblin Cave scene)
	fBeenIn51 			;41
	fBeenIn52 			;42
	fBeenIn53 				;43
	fBeenIn54 	;44
	fBeenIn55 	;45
	fBeenIn56 			;46
	fBeenIn57 			;47
	fBeenIn58 			;48
	fBeenIn59 			;49 ;unused (Room 59 does not exist)
	fBeenIn60 				;50
	fBeenIn61 			;51
	fBeenIn62 			;52
	fBeenIn63 			;53
	fBeenIn64 	;54
	fBeenIn65 		;55
	fBeenIn66 			;56
	fBeenIn67 			;57
	fBeenIn68 			;58
	fBeenIn69 			;59
	fBeenIn70 			;60 ;unused
	fBeenIn71 			;61
	fBeenIn72 			;62
	fBeenIn73 		;63 ;unused
	fBeenIn74 			;64
	fBeenIn75 			;65
	fBeenIn76 				;66
	fBeenIn77 			;67
	fBeenIn78 			;68
	fBeenIn79 			;69
	fBeenIn80 			;70
	fBeenIn81 			;71 ;log crossroads
	fBeenIn82 		;72
	fBeenIn83 		;73
	fBeenIn84 			;74
	fBeenIn85 			;75
	fBeenIn86 			;76
	fBeenIn87 				;77
	fBeenIn88		 			;78	;room removed from VGA; will be used again in Restoration Patch
	fBeenIn89		 			;79
	fBeenIn90		 			;80 ;unused
	fBeenIn91			 		;81
	fBeenIn92 					;82
	fBeenIn93			 		;83
	fBeenIn94				 	;84
	fBeenIn95 					;85 ;unused
	fBeenIn96 					;86
	fBeenIn97			 		;87
	fBeenIn300			 		;88
	fBeenIn301					;89
	fBeenIn302				 	;90
	fBeenIn311				 	;91
	fBeenIn313		 			;92
	fBeenIn314 	;93
	fBeenIn320		;94
	fBeenIn321 		;95
	fBeenIn322 				;96
	fBeenIn330 		;97
	fBeenIn331 		;98
	fBeenIn332 		;99
	fBeenIn333		 			;100
	fBeenIn334			 		;101
	fNextMonster				;102	;for Brigands and Goblins, check if any are still alive
	fUnused103					;103
	fUnused104					;104
	fUnused105					;105
	fUnused106					;106
	fUnused107					;107
	fUnused108					;108
	fUnused109					;109
	fWornOut		 			;110
	fHaveOrdered	 			;111
	fShemaBringsOrder 			;112
	fTookBarNote 			;113
	fBeenInGraveyardNight		;114	;NOTE: this VISITED flag is outside of the regular visited flags.
	fMerchantAtInn	 			;115
	fMetMerchant				;116
	fBarThrownOut 				;117
	fBarDrunk 					;118
	fMetShopkeeper				;119
	fPurchasedFromShop 	;120
	fUnused121 		;-----------event 121 is unused
	fRentedRoom					;122
	fLearnedThiefPassword 		;123
	fHungry		 				;124
	fStarving	 				;125
	fAmbushedAlley				;126
	fSignedLogbook 				;127
	fSleptAtInn 				;128
	fSaveAllowed 				;129	; game saving is disabled in certain rooms, like during battles.
	fSearchedCouch 			;130
	fSearchedBasket 		;131
	fSearchedPurse 			;132
	fStoleCandles 			;133
	fSearchedDesk 			;134
	fUncoveredCage 			;135
	fGotSeed					;136
	fKilledFlower1 			;137
	fKilledFlower2 			;138
	fKilledFlower3 			;139
	fPickedEranaFlowers 		;140
	fLearnedCalm				;141
	fErasmusWarpOut 			;142
	fWrongAnswer 				;143
	fSearchedDrawer 	;144
	fSearchedSafe 		;145
	fUnused146		;----------- event 146 is unused
	fGotKoboldKey		 		;147
	fHaveToadstools		 		;148	; replaced flag 174 in 1.200
	fBearCaveMessage 		;149	; used to trigger the inital entrance message
	fKoboldDead		 			;150
	fKoboldChestUnlocked		;151
	fUnused152		;----------- event 152 is unused
	fKoboldAwake 				;153
	fAttackedErasmus 			;154
	fSawRedSign				;155
	fSawGreenSign 				;156
	fUnused157		;----------- event 157 is unused	
	fMantrayLeft 				;158
	fMantrayRight 				;159
	fBearFriendly 				;160
	fBearGone 					;161
	fUnused162		;----------- unused
	fUnused163		;----------- events 162-163 are unused
	fGhostsAttack 				;164
	fTookToadstools 		;165
	fFightingKobold 			;166
	fMetBear					;167
	fGhostOil		 			;168	;protected by Undead Unguent
	fBearDying					;169
	fSavedBarnard				;170
	fMetSkull				;171
	fHutOnGround		;172	; originally used for the hut being open; that flag is now 287
	fBabaFlyAway			;173
	fKoboldCastingReversal		;174	; originally used for checking if player has Kobold Toadstools
	fKoboldChestSearched		;175
	fFlag176					;176	;Replaced with the global variable "timesEntered67"
	fFlag177			;177
	fUnused178		;----------- event 178 is unused
	fStoleVase 			;179
	fMovedVase 			;180
	fStoleCandelabra 	;181
	fStoleMusicBox 		;182
	fUncoveredSafe 		;183
	fCrackedSafe 		;184
	fUnused185		;----------- event 185 is unused
	DANCED_FOR_FAIRIES 			;186
	DANCING_FOR_FAIRIES 		;187
	fFaeryAttention		 		;188
	fKarlAttention 			;189
	fClimbedSpireaLedge			;190
	fLeftTown				;191
	fFlowersInactive			;192
	fOttoBackToBed 			;193
	fOpenMusicBox 				;194
	fOttoClosedMusicBox 		;195
	fWokeUpSheriff 			;196
	fMetDryad 					;197
	fAgreedToHelpDryad 			;198
	fLeftTown2 				;199
	fUnused200		;----------- event 200 is unused
	fLearnedDispel 		;201
	fStagHere 				;202
	fStagHurt 					;203
	fAntwerpInSky 				;204
	fOverloaded			 		;205
	fAntwerpSplit 				;206
	ATTACKED_BUSH_GOBLIN		;207	; Was used for meeting the fox in QFG1EGA; now it's used for attacking the Goblin who's hiding in a bush.
	OBTAINED_GEM 				;208
	LOOKED_IN_PTERESA_NEST 		;209
	OBTAINED_RING 				;210
	fFlag211 					; event 211 is tested outside Healer's house, but is never set
	PTERESA_LEFT_NEST 			;212
	WHACKED_BY_GIANT 			;213
	UNUSED_214		;----------- event 214 is unused
	OBTAINED_BARNARD_REWARD 	;215
	fKoboldChestKnown			;216
	fStableClean 				;217
	DISPEL_GAVE_DUST 			;218
	DISPEL_GAVE_FUR 			;219
	DISPEL_GAVE_FLOWERS 		;220
	DISPEL_GAVE_ACORN 			;221
	DISPEL_GAVE_WATER 			;222
	DISPEL_HEALER_MAKING_POTION ;223
	OBTAINED_DISPEL_POTION 		;224
	MET_HEALER 					;225
	fStolePotions		 		;226	; banned from Healer's hut
	OBTAINED_GREEN_FUR 			;227
	LEARNED_DETECT 				;228
	MET_STABLEMAN 				;229
	RETURNED_RING 				;230
	fUnused231					;----------- event 231 is unused
	fFoundGoblinHideout 		;232
	fMonsterDazzled				;233
	fUnused234					;----------- event 234 is unused
	SPIED_THIEVES 				;235
	HENRY_LADDER_KNOWN 			;236
	fMinotaurDead	 			;237	; He's not actually dead, just unconscious
	fBrigGateOpen	 			;238
	fFlag239 					;EVENT_FIGHTINGWEAPONMASTER?? Unsure
	fDeadlyTP 			;240	; teleported dangerously away by henry.
	fSafeTP 				;241	; teleported away safely by Henry
	fAskedForTrigger 		;242
	fFlag243 					; Event 243 is related to the Weapons Master
	BRIGANDS_BEHIND_LOG 		;244
	fGotBrutusKey 		;245
	fSawNessie 			;246
	fHidenGoseke 			;247
	fDiedInBattle		 		;248
	fBeatMaster 		;249
	fBeatFred 				;250
	fBeatFred89 			;251
	fTrollDoorUnlocked 		;252
	fTrollDoorOpen 			;253
	fEgoSitting	 				;254
	fOrderedDrink				;255
; Events 256-260 are related to Yorick's maze
	fFlag256
	fOpeningLeaderDoor		;257
	fFlag258
	fPulledChain				;259
	fFlag260

	fBeatBrutus			;261
	fLearnedTrigger			;262
	fFlag263					;263 (used in Yorrick's room)
	fFlag264					;264 (used in brigandDoorFall)
	fFlag265					;265 (used in Yorrick's room) 
	fErasmusAskedMaze 	;266
	fNoMoreTalking 		;267
	fBefriendedYorick 			;268
	fFlag269					; set in brigandHappyFace
	fFlag270					; set in brigandFallSideways
	fFlag271					;271 (tested in Yorrick's room)
	fClimbedHenryCliff			;272
	fSavedElsa 					;273
	fFlag274					;274 used in hermitHits
	fKnockedOnHenryDoor 		;275
	fFlag276					;276 used in hermitHits
	fFlag277					;277 used in healerGetNest
	fClimbedTree		;278
	fHaveFaeryShrooms		;279
	fFlag280					;280 (set in Kobold Flame Dart)
	fFlag281				;281 (set in Kobold Flame Dart)
	fKoboldChestExploded 		;282
	fFlag283					;283 (cleared in Kobold Flame Dart)
	fFlag284		;----------- 
	fFlag285		;----------- 
	fShroomTrip					;286
	fBabaHutOpen			;287	; Replaces flag 172, which now is set when Baba Yaga's hut is on the ground but not yet accessible.
	fSawDelphineus			;288
	fUnused289
	fSkullOfferedDeal		;290
	fMadeDealWithSkull		;291
	fUnused292
	fTownGateOpen				;293
	fOgreChestKnown			;294
	fUnused295
	fFatigued					;296	; "You are getting tired."
	fUnused297
	fUnused298
	fSlinkWarned				;299
	fShameenStands	 			;300
	fShemaAsks		 			;301
	fEatenAtInn 				;302
	fSittingAtInn				;303
	fStopFightingMaster		 	;304
	fMetMeepBoss 				;305
	fUnused306
	fUnused307
	fBeardKnown		;308
	fFlag309					;309	;flags 309 and 310 are tested at the endgame ceremony, but are never set
	fFlag310					;310
	fUnused311
	fTalkedToBear				;312
	fUnused313
	fUnused314
	fUnused315
	fUnused316
	fUnused317
	fUnused318
	fUnused319
	fUnused320
	fOfferedTraining 			;321
	fFlag322
	fFarmerIsEast 				;323
	fBrutusWaits				;324
	fBabaFrog 					;325
	fBabaCurse	 				;326
	fAteFaeryShrooms 		;327
	fInMainGame 				;328	; This flag is set when we're in the game proper.
	fHaveFlyingWater			;329
	fHaveLakeWater 			;330
	fBeatOgre 				;331
	fOpenedOgreChest 			;332
	fSearchedOgreChest	 		;333
	fHealerKnowsDispel		 	;334
	fSearchedFredTreasure 		;335
	fGaveSkullGem	 			;336
	fStopMaze 			;337
	fFedHenry		 			;338	; Can sleep at the Hermit's cave with this flag set
	fUnused339		;----------- 
	fShavedFred 				;340
	fOgreGone 					;341	; Ogre disappears day after his death
	fUnused342		;----------- 
	fUnused343		;----------- 
	fUnused344		;----------- 
	fFastEgo					;345 (unused in VGA)
	fUnused346		;----------- 
	fGenerousToMerchant 		;347
	fBrigsUnaware			;348
	fUnused349		;----------- 
	fClawsKnown					;350
	fFlag351					;351 set in arenaMantrayFight after killing the mantaray
	fHenryDoorOpen 			;352
	fWizKnowsEgoHasMagic ;353
	fMetGargoyle				;354
	fReadBarnardBulletin 		;355
	fLearnedRhyme				;356
	fFlag357					;set in egoThrust
	fUnused358					;358
	fHideCursor					;359
	fCharSheetActive			;360
	fFlag361					;related to picking up dropped items
	fDaggerInBrutus				;362	; if you killed Brutus by throwing a dagger at him, you can pick it up from his body later.
)
									 
; Puzzle Point Event Flags (142 flags allocated; 6 flags unused: 609, 692, 710, 712, 714, 716)
(enum 601
	
	f420BeatBear	 	;601)             ; -25 points
	f15BeatKbold 	;602)           ; 10 points (Fighter or Magic User only)
	f10KillFlower 	;603)          ; -10 points
	POINTS_KILLFOX 		;604)              ; -10 points
	POINTS_GOTDRUNK 	;605)             ; -5 points
; events 606 to 644 are character specific points, listed at bottom.

	f334Robbed 	;606)            ; -10 points (Thief only)

; FIGHTER ONLY POINTS
	POINTS_FIGHTWEAPONMASTER ;607)        ; 3 points (Fighter only)
	POINTS_DEFEATWEAPONMASTER ;608)       ; 10 points (Fighter only)
; no event 609
	POINTS_UNUSED_609
	POINTS_BUYCHAINMAIL ;610)         ; 3 points (Fighter only)
	f450BeatTroll 	;611)            ; 4 points (Fighter only)
	f440BeatCheetaur	;612)         ; 4 points (Fighter Only)
	f460BeatDragon	;613)        ; 4 points (Fighter Only)
;remaining Fighter only Points are at the bottom:

; MAGE ONLY POINTS
	 f10LearnCalm ;614)            ; 4 points (Mage only)
	 f10SleepInMeadow ;615)           ; 5 points (Mage only)
	 POINTS_PLAYWIZARDGAME ;616)       ; 5 points (Mage only)
	 POINTS_WINWIZARDGAME ;617)        ; 12 points (Mage only)
	 POINTS_LEARNDETECTMAGIC ;618)         ; 4 points (Mage only)
	 POINTS_LEARNTRIGGER ;619)         ; 4 points (Mage only)
	 POINTS_LEARNFETCH ;620)           ; 2 points (Mage only)
	 POINTS_LEARNOPEN ;621)            ; 2 points (Mage only)
	 POINTS_LEARNFLAMEDART ;622)       ; 2 points (Mage only)
	; THIEF ONLY POINTS
	 POINTS_ENTERLOLHOUSE ;623)        ; 5 points (Thief Only)
	 POINTS_SEARCHLOLDESK ;624)        ; 1 point (Thief Only)
	 POINTS_SEARCHLOLCOUCH ;625)       ; 1 point (Thief Only)
	 POINTS_SEARCHLOLPURSE ;626)       ; 1 point (Thief Only)
	 POINTS_SEARCHLOLBASKET ;627)      ; 1 point (Thief Only)
	 POINTS_TAKECANDLESTICKS ;628)         ; 1 point (Thief Only)
	 POINTS_PETLOLCAT ;629)            ; 3 points (Thief Only)
	 POINTS_ENTERSHERIFFHOUSE ;630)        ; 5 points (Thief only)
	 POINTS_SEARCHSHERIFFDRAWER ;631)      ; 1 point (Thief only)
	 POINTS_MOVEPAINTING ;632)         ; 1 point (Thief only)
	 POINTS_CRACKSAFE ;633)            ; 1 point (Thief only)
	 POINTS_TAKESAFEMONEY ;634)       ; 1 point (Thier only)
	 POINTS_TAKEVASE ;635)             ; 1 point (Thief only)
	 POINTS_TAKECANDELABRA ;636)       ; 1 point (Thief only)
	 POINTS_TAKEMUSICBOX ;637)         ; 1 point (Thief only]
	 POINTS_ENTERTHIEVESGUILD ;638)        ; 5 points (Thief Only)
	 POINTS_BUYTHIEFLICENSE ;639)      ; 3 points (Thief Only)
	 POINTS_BUYTHIEFTOOLKIT ;640)      ; 3 points (Thief Only)
	 POINTS_FENCEGOODS ;641)           ; 3 points (Thief only)
	 POINTS_SHOWTHIEFSIGN ;642)        ; 3 points (Thief only)
	 f340PlayDagNabIt ;643)         ; 3 points (Thief Only)
	 f340WinBigBet ;644)         ; 5 points (Thief Only)
	
	; All class points
	 f10EatMeadowFruit ;645)        ; 2 points
	 f14CalmBear ;646)             ; 5 points
	 f14FreeBear ;647)             ; 25 points
	 f15EnterCave ;648)      ; 2 points
	 f15GetKey ;649)        ; 7 points
	 f15GetTreasure ;650)      ; 5 points
	 f16GetSeed ;651)              ; 8 points
	 f21EnterHut ;652)     ; 2 points
	 POINTS_TURNBABAYAGAINTOFROG ;653)     ; 50 points
	 POINTS_GIVEMANDRAKEROOT ;654)     ; 3 points
	 f22MakeTheDeal ;655)        ; 2 points
	 f22GiveGem ;656)              ; 10 points
	 f22HutSit ;657)               ; 7 points
	 POINTS_ENTERERASMUSCASTLE ;658)       ; 3 points
	 POINTS_TALKTOERASMUS ;659)        ; 1 point
	 POINTS_ENTERCASTLECOURTYARD ;660)         ; 1 point
	 POINTS_TALKTOWEAPONMASTER ;661)       ; 1 point
	 POINTS_WORKINSTABLES ;662)        ; 5 points
	 f141MeetBaron ;663)            ; 10 points
	 f141TalkToBaron ;664)          ; 3 points
	 POINTS_TALKTOFARMER ;665)         ; 1 points
	 POINTS_TALKTOFARMERABOUTLEADER ;666)     ; 3 points
	 POINTS_GETGOLDRING ;667)          ; 3 points
	 POINTS_TALKTOHEALER ;668)         ; 2 points
	 POINTS_RETURNRING ;669)           ; 10 points
	 POINTS_SELLMUSHROOM ;670)         ; 1 point
	 POINTS_SELLCHEETAURCLAW ;671)     ; 2 points
	 POINTS_SELLTROLLBEARD ;672)       ; 2 points
	 POINTS_SELLFLOWERS ;673)          ; 1 points
	 POINTS_GIVEFLYINGWATER ;674)      ; 2 points
	 POINTS_GIVEMAGICACORN ;675)       ; 5 points
	 POINTS_GIVEGREENFUR ;676)         ; 2 points
	 POINTS_GIVEFAIRYDUST ;677)        ; 2 points
	 POINTS_GETDISPELPOTION ;678)      ; 7 points
	 POINTS_GETGLOWINGGEM ;679)        ; 8 points
	 POINTS_TALKTOMEEP ;680)           ; 1 point
	 POINTS_GETGREENFUR ;681)          ; 5 points
	 POINTS_USEUNDEADUNGUENT ;682)     ; 2 points
	 POINTS_GETMANDRAKEROOT ;683)      ; 6 points
	 POINTS_LEAVETOWNFIRSTTIME ;684)       ; 1 point
	 POINTS_RECEIVEBADADVICEFROMBRUNO 	;685)        ; 2 points
	 POINTS_FREEFOX 					;686)              ; 10 points
	 POINTS_TALKTOFAIRIES 				;687)            ; 1 point
	 POINTS_DANCEWITHFAIRIES 			;688)         ; 3 points
	 POINTS_GETFAIRYDUST 				;689)         ; 8 points
	 POINTS_PICKMUSHROOMS 				;690)        ; 3 points
	 POINTS_OVERHEARBRUNO 				;691)        ; 12 points
	 POINTS_UNUSED_692
	; no event 692
	 POINTS_AGREETOHELPDRYAD 			;693)     ; 1 point
	 POINTS_GIVESEED 					;694)             ; 7 points
	 POINTS_GETACORN 					;695)             ; 1 point
	 fEatAcorn 					;696)             ; -5 points
	 POINTS_KNOCKONHERMITDOOR 			;697)        ; 1 point
	 POINTS_GETFLYINGWATER 				;698)       ; 3 points
	 POINTS_MEETHERMIT 					;699)           ; 5 points
	 POINTS_TALKTOHERMIT 				;700)         ; 2 points
	 POINTS_FINDSECRETENTRANCE 			;701)       ; 10 points
	 POINTS_GIVECAVEPASSWORD 			;702)         ; 5 points
	 POINTS_VISITLAKE 					;703)            ; 1 point
	 POINTS_ENTERSECRETENTRANCE 		;704)      ; 2 points
	 f94EnterFortress 		;705)     ; 8 points
	f95EnterDiningRoom 		;706)       ; 8 points
	f96EnterJesterRoom 		;707)       ; 8 points
	f96TalkToJester 				;708)         ; 2 points
	f96AskAboutElsa 		;709)        ; 8 points
	fUnused710							;710
	f97EnterLeaderRoom 		;711)       ; 12 points
	
	POINTS_UNUSED_712					; no event 712
	f172DispelLeader 		;713)          ; 35 points
	POINTS_UNUSED_714					; no event 714
	POINTS_TAKEMAGICMIRROR 			;715)      ; 10 points
	POINTS_UNUSED_716
	; no event 716
	 f300EnterTown 						;717)            ; 1 point
	 f300TalkToSheriff 					;718)        ; 1 point
	 f301TalkToShameen				;719)        ; 1 point
	 f301TalkToAbdulla	 				;720)         ; 5 points
	 f301OrderMeal 						;721)         ; 1 point
	 f301RentRoom					;722)             ; 1 point
	 f311ReadBook 				;723)          ; 4 points
	 f311SignBook 				;724)          ; 1 point
	 f311TalkToWolfgang 			;725)        ; 1 point
	 f311ReadBoard		 			;726)      ; 6 points
	 POINTS_TALKTOZARA 					;727)           ; 1 point
	 POINTS_BUYAPPLES 					;728)            ; 3 points
	 POINTS_TALKTOCENTAURGIRL 			;729)        ; 1 point
	 POINTS_TALKTOSHOPKEEPER 			;730)         ; 1 point
	 POINTS_PICKUPNOTE 					;731)           ; 2 points
	 f333TalkToBeggar				;732)         ; 1 point
	 f333GiveAlms					;733)             ; 1 point
	 f600EndGame 						;734)              ; 25 points
	 POINTS_TALKTOGATEKEEPER 			;735)         ; 5 points
	 f301GaveMerchantFood 				;736)       ; 2 points
	
	;remaining Fighter Only points.
	 f425BeatMinotaur 					;737)         ; 5 points (Fighter only)
	 f435BeatMantray 				;738)          ; 2 points (Fighter only)
	 f455BeatOgre 					;739)             ; 2 points (Fighter only)
	 f465BeatBrigand				;740)          ; 1 point (Fighter Only)
	 f430BeatSaurus 					;741)           ; 1 point (Figher only)
	 f445BeatGoblin 					;742)           ; 1 point (Fighter Only)
)	
