;;; Sierra Script 1.0 - (do not remove this comment)

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
	fBeenIn88		 			;78
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
	fBeenIn310				 	;90
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
)

;These are event flags.  They are used to track certain On/Off actions throughout the game.	
(enum 110
	fWornOut		 			;110
	fHaveOrdered	 			;111
	fShemaBringsOrder 			;112
	fTookBarNote 				;113
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
	fHutOnGround		;172
	fBabaFlyAway			;173
	fKoboldCastingReversal		;174	; originally used for checking if player has Kobold Toadstools
	fKoboldChestSearched		;175
	fFirstTimeIn67					;176
	fSecondTimeIn67					;177
	fUnused178		;----------- event 178 is unused
	fStoleVase 			;179
	fMovedVase 			;180
	fStoleCandelabra 	;181
	fStoleMusicBox 		;182
	fUncoveredSafe 		;183
	fCrackedSafe 		;184
	fUnused185		;----------- event 185 is unused
	fDancedForFairies 			;186
	fDancing 		;187
	fFaeryAttention		 		;188
	fKarlAttention 			;189
	fClimbedSpireaLedge			;190
	fLeftTown				;191
	fFlowersInactive			;192
	fOttoAwakened 			;193
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
	fMetFox 					;207
	fGotGem 				;208
	fLookedInNest 		;209
	fGotRing 				;210
	fFlag211 					; event 211 is tested outside Healer's house, but is never set
	fNestAbandoned 			;212
	fHitByGiant 			;213
	fUnused214		;----------- event 214 is unused
	fBarnardReward			 	;215
	fKoboldChestKnown			;216
	fStableClean 				;217
	fGaveDust		 			;218
	fGaveFur 			;219
	fGaveFlowers 		;220
	fGaveAcorn 			;221
	fGaveWater 			;222
	fHealerMakingDispel ;223
	fGotDispel 		;224
	fMetHealer 					;225
	fStolePotions		 		;226	; banned from Healer's hut
	fGotFur 			;227
	fLearnedDetect 				;228
	fMetStableman 				;229
	fReturnedRing 				;230
	fUnused231					;----------- event 231 is unused
	fFoundGoblinHideout 		;232
	fMonsterDazzled				;233
	fUnused234					;----------- event 234 is unused
	fSpiedOnThieves 				;235
	fLadderKnown 			;236
	fMinotaurDead	 			;237	; He's not actually dead, just unconscious
	fBrigGateOpen	 			;238
	fMasterIsHere				;239
	fDeadlyTP 			;240	; teleported dangerously away by henry.
	fSafeTP 				;241	; teleported away safely by Henry
	fAskedForTrigger 		;242
	fMasterShowedOff 		;243
	fBrigsBehindLog 		;244
	fGotBrutusKey 		;245
	fLakeEasterEgg 			;246
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
	fFallTrapdoor			;256
	fOpeningDoor		;257
	fFallingOffLedge		;258
	fPulledChain				;259
	fRollingOut				;260
	
	fBeatBrutus			;261
	fLearnedTrigger			;262
	fGoThruDoorway					;263 (used in Yorrick's room)
	fFakeDoorDown				;264 (used in brigandDoorFall)
	fFlag265					;265 (used in Yorrick's room) 
	fErasmusAskedMaze 	;266
	fNoMoreTalking 		;267
	fBefriendedYorick 			;268
	fBallConks					;269 ; set in brigandHappyFace
	fFallTrap4					;270 ; set in brigandFallSideways
	fYorickThrows					;271 (tested in Yorrick's room)
	fClimbedHenryCliff			;272
	fSavedElsa 					;273
	fEgoFallOffCliff			;274 used in hermitHits
	fKnockedOnHenryDoor 		;275
	fEgoSquashed				;276 used in hermitHits
	fLeavingCastle					;277 used in healerGetNest
	fClimbedTree		;278
	fHaveFaeryShrooms		;279
	fFlag280					;280 (set in Kobold Flame Dart)
	fFlag281				;281 (set in Kobold Flame Dart)
	fKoboldChestExploded 		;282
	fFlag283					;283 (cleared in Kobold Flame Dart)
	fUnused284		;----------- 
	fUnused285		;----------- 
	fUnused286		;----------- 
	fUnused287		;----------- 
	fUnused288		;----------- 
	fUnused289		;----------- 
	fUnused290		;----------- 
	fUnused291		;----------- 
	fUnused292		;----------- 
	fUnused293		;----------- 
	fUnused294
	fUnused295
	fUnused296
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
	fUnused308
	fUnused309
	fUnused310
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
	fFastEgo					;345 (unused in 1.200)
	fUnused346		;----------- 
	fGenerousToMerchant 		;347
	fBrigsUnaware			;348
	fUnused349		;----------- 
	fUnused350		;----------- 
	fMantrayDies					;351 set in arenaMantrayFight after killing the mantaray
	fHenryDoorOpen 			;352
	fWizKnowsEgoHasMagic ;353
	fMetGargoyle				;354
	fReadBarnardBulletin 		;355

)