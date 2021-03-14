;;; Sierra Script 1.0 - (do not remove this comment)
(include system.sh) (include sci2.sh)
(include verbs.sh) (include talkers.sh)

;Global scripts
(define GLORY_INIT			1)
(define TIME				7)
(define TARGET				8)
(define CASTAREA			12)
(define CASTOPEN			13)
(define CHARSHEET			15)
(define GLORY_INV			16)
(define SPEED				17)
(define GLORY_DEBUG			18)
(define GLORY_ABOUT			19)
(define GLORY_ACTIONS		20)
(define GLORY_MAGIC			21)
(define GLORY_WINDOW		22)
(define TELLER				23)
(define GLORY_CONTROL		25)
(define EGODEAD				26)
(define STARTAROOM			27)
(define GLORY_EGO			28)
(define CHEST				29)
(define OPEN_GIFT			30)
(define LEVITATION			31)
(define PROJECTILE 			32)
(define OCC_CYCLE			33)
(define UHURA_TALKER		34)
(define RAKEESH_TALKER		35)
(define JOHARI_TALKER		36)
(define CASTFETCH			37)
(define ARRAY_PATH			38)
(define YESUFU_TALKER		39)
(define HARAMI_TALKER		40)
(define MONkEY_TALKER		41)
(define LAIBON_TALKER		42)
(define SHAMAN_TALKER		43)
(define LEADER_TALKER		44)
(define STAFF_SCRIPT		46)
(define VENDOR				47)
(define PRIESTESS_TALKER	48)
(define KREESHA_TALKER		49)
(define PANORAMA			50)
(define BAZAAR				51)
(define CHARSAVE			52)
(define STORY_TALKER		53)
(define IMPORT				54)
(define COMBAT_CONTROLS		56)
(define JUMPX				57)
(define ELDER_TALKER		58)
(define GLORY_TALKER		59)
(define INTRO				61)
(define CASTJUGGLE			62)
(define OPENING_CREDITS		63)
(define OPENING				100)
(define WHERE_TO			101)
(define CHARSEL				140)
(define INIT_ADDTOS			403)
(define MCHASE				404)

; howFast values
(enum
	slow
	medium
	fast
	fastest
)

; General Game Defines
(define NUM_STATS 5)
(define NUM_SKILLS 10)
(define NUM_DERIVS 5)
(define NUM_SPELLS 14)
(define NUM_ATTRIBS 34) ;(+ NUM_STATS NUM_SKILLS NUM_DERIVS NUM_SPELLS)
(define NUM_INVITEMS 46) ;(- iLastInvItem 1)

(define QG1_NUM_ATTRIBS 25)
(define	QG2_NUM_ATTRIBS 30)

(define GAMEDAY 3600)	;number of ticks per game day
(define GAMEHOUR 150) 	;number of ticks per game hour

; Inventory
(enum
	iRoyals 	;0		;Dinars and Commons are stored in global variables 424 and 410, respectively.
	iSword		;1
	iFineDagger	;2
	iFineSpear	;3
	iChainmail	;4
	iShield		;5
	iGrapnel	;6
	iToolkit	;7
	iGuildCard	;8		;Unused; item data and description exist for this, but there is no corresponding icon.
	iTinderbox	;9
	iDaggers	;10
	iCurePills	;11
	iHealPills	;12
	iManaPills	;13
	iRations	;14
	iWaterskin	;15
	iDispell	;16
	iFish		;17
	iMeat		;18
	iFruit		;19
	iBeads		;20
	iSkins		;21
	iHorn		;22
	iRocks		;23
	iVine		;24
	iOil		;25
	iRope		;26
	iGagGift	;27
	iPin		;28
	iHoney		;29
	iFeather	;30
	iAmulet		;31		;Unused; you can't buy from the Amulet seller, and there is no corresponding icon or description.
	iLeopard	;32
	iBird		;33
	iOpal		;34
	iVineFruit	;35
	iGem		;36
	iPeaceWater	;37
	iHeartGift	;38
	iOrchid		;39
	iRobe		;40
	iBridge		;41
	iEye		;42		;Unused; the Anubis statue just has the Fire Opal item, and there is no corresponding icon.
	iNote		;43
	iWood		;44
	iMagicSpear	;45
	iMagicDrum	;46
	iLastInvItem	;this marks where inventory items end
)

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

; Character Classes
(enum
	FIGHTER
	MAGIC_USER
	THIEF
	PALADIN
)

; Bargain results
(enum 1
	 bargainSUCCESS
	 bargainTRY1
	 bargainTRY2
	 bargainTRY3
	 bargainFAIL
	 bargainNODEAL
)

; Puzzle points by character class
(define puzzleFIGHTER $0001)
(define puzzleWIZARD $0002)
(define puzzleTHIEF $0004)
(define puzzlePALADIN $0008)

; Ego Moving Modes
(enum
	MOVE_WALK
	MOVE_RUN
	MOVE_SNEAK
)

; Action Bar Icons
(define ACTION_RUN $0001)
(define ACTION_SNEAK $0002)
(define ACTION_REST $0004)

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
	COMM		;13
	HONOR		;14
	EXPER		;15		;Unused
	HEALTH		;16
	STAMINA		;17
	MANA		;18
; Magic Spells
	OPEN				;19
	DETMAGIC			;20
	TRIGGER				;21
	DAZZLE				;22
	ZAP					;23
	CALM				;24
	FLAMEDART			;25
	FETCH				;26
	FORCEBOLT			;27
	LEVITATE			;28
	REVERSAL			;29
	JUGGLE				;30
	STAFF				;31
	LIGHTNING			;32
	HEALING				;33
	ENDSTATS			;34	;scrapped spell?
)

; Battle results
(enum
	battleEGOLOST
	battleEGOWON
	battleEGORUNS
)

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

;Sword states
(enum
	swordNormal
	swordMagic
)

;Event flags
(enum 1
	fWornOut				;1
	fHungry					;2
	fStarving				;3
	fUnused4				;4
	fOverloaded				;5
	fInMainGame				;6
	fFlag7					;7
	fReversal				;8
	fListenedToStory		;9
	fFlag10					;10
	fGaveHorn				;11
	fSoulJudged				;12
	fGaveDrum				;13
	fFlag14					;14
	fCastingSpell			;15
	fAfterMatch				;16
	fUnused17				;17
	fCantEnterSimbani		;18	;this flag should be set after the peace conference, but it never is
	fCantBePaladin			;19
	fTarnaClosed			;20
	fUnused21				;21
	fHaramiRobbedChanger	;22
	fRanAfterHarami			;23
	fTrippedHarami			;24
	fDaggeredHarami			;25
	fFlamedHarami			;26
	fUsedCalmOnHarami		;27
	fDidntCatchHarami		;28
	fLeopardmanCaptured		;29
	fHelpedYesufu			;30
	fFlag31					;31
	fMetAardvark			;32
	fUnused33				;33
	fFlag34					;34
	fRakeeshSworeOath		;35
	fHaramiWasOnTrial		;36
	fSawLeopardmanRitual	;37
	fDispelledLeopardman	;38
	fUnused30				;39
	fMetHonorlessHarami		;40
	fMetMoneyChanger		;41
	fVisitedSimbani			;42
	fAfterConference		;43
	fHadPaladinCeremony		;44
	fUnused45				;45
	fHaramiSecondVisit		;46
	fHaramiThirdVisit		;47
	fMetRajah				;48
	fMetLaibon				;49
	fLaibonHutRepaired		;50
	fGotGem					;51
	fMeerbatDead			;52
	fPracticedWithUhuraOnce	;53
	fVisitedVines			;54
	fMeerbatFreed			;55
	fAfterRace				;56
	fFoundReeshaka			;57
	fAskedAboutDispel		;58
	fBalanceBridge			;59
	fEnteredTemple			;60
)
(define fBeenInLostCityPanorama	63)
(define fPracticedWithUhuraTwice		64)
(define fJohariReleased		65)
(define fDayElderAnnouncement	67)
(define fNightElderAnnouncement	68)
(define fCanSummonStaff 69)
(define fDayDispelMsg	70)
(define fNightDispelMsg	71)
(define fDayCongratsMsg	72)
(define fNightCongratsMsg	73)
(define fStoleDrum 74)
(define fEnteredMirrorRoom	77)
(define fKnockedOverFruit	80)
(define fEgoIsAsleep 81)
(define fDeWizBattle	82)
(define fHoneyBirdHinted	83)
(define fSpearedWiz	84)
(define fCanGoToSimbani	85)
(define fFirstEnteredSimbaniOverlook	86)
(define fFirstEnterSimbaniVillage 87)
(define fTravelWithSomeone	88)
(define fDeWizElectrocuted	89)
(define fChuckedSwordAtWiz	91)
(define fGoWithJohari	92)
(define fGoWithManu					93)				
(define fCanGoToLeopardmanVillage	94)
(define fWillGoToWaterfall			95)
(define fMonkeysFindDeWorm			96)
(define fDeWizTookStaff		97)
(define fSawImpala			98)
(define fFoundHoneyBird		99)
(define fCharSheetActive 101)
(define fEnteredLeopardmanVillage 102)
(define fMetWeaponSeller	106)
(define fMetSanfordAndSon	107)
(define fGaveKattaNote		108)
(define fManuReleased	109)
(define fManuAbandoned1	110)
(define fManuAbandoned2	111)
(define fFedHarami 113)
(define fBoughtCloth 114)
(define fPoisoned 115)
(define fGotOrchid 116)
(define fWonGame	117)
(define fAfterStory	118)
(define fSpeedDisabled 119)
(define fFlag121 121)
(define fWitnessedRobbery	122)
(define fKnockingOverFruit	123)
(define fWizNoticesEgo	124)
(define fFlag125 125)
(define fWillHaveMeeting 130)
(define fNightPaletteDisabled 131)
(define fEnteredGuardianDen	132)
(define fNeedStaff	133)
(define fAfterWaterfall	134)
(define fVisitedBazaar 135)
(define fLostCityDoorOpen	136)
(define fFakeDeath 140)
(define fStoleFromDrummer	141)
(define fSawKreeshaReading	142)
(define fStartedEncounter	143)
(define fWasWizard	144)
(define fGiftedCarvedLeopard	146)
(define fGotBlackBird	147)
(define fGotOpalAndFruit	148)
(define fTrainedAcrobatics	149)
(define fSenseDanger 150)
(define fHonorShield 151)
(define fInitiationComplete	154)
(define fReturnedToRakeesh	156)
(define fBabaFrog 158)
(define fMetHarami	159)
(enum 161
	fPrisonerEasterEgg	;161
	fEgoHasDied			;162
	fGotDrum			;163
	fUnused164			;164
	fShamanDead			;165
	fGotFineDagger		;166
	fGotFineSpear		;167
	fGotRope			;168
	fGotCarvedLeopard	;169
	fGotRobe			;170
	fDidMoneyExchange	;171
	fHaramiGone			;172
)

(enum 202
	fLearnFlamingSword				;202
	fLearnHealing					;203
	fLearnSenseDanger				;204
	fLearnHonorShield				;205
	fLearnJugglingLights			;206
	fUnused207						;207
	fExitTarna						;208
	fEnteredSimbaniPanorama			;209
	fEnteredGiantTreePanorama		;210
	fEnteredLostCityPanorama		;211
	fBeatDemonWormForMonkeys		;212
	fExchangedDinars				;213
	fLetHaramiEscape				;214
	fStopHaramiWithMagic			;215
	fChaseHarami					;216
	fHaramiHitByDagger				;217
	fHaramiSlipsOnFruit				;218
	fBoughtZebraSkin				;219
	fBoughtWaterskin				;220
	fTellHaramiAboutRakeesh			;221
	fBoughtFineSpear				;222
	fBoughtOil						;223
	fBoughtHoney					;224
	fBoughtTinderbox				;225
	fBoughtBlackBird				;226
	fBoughtFineDagger				;227
	fBuyRope						;228
	fShowSignToHarami				;229
	fAgreedToMeetHarami				;230
	fBuyBeads						;231
	fBuyCarvedLeopard				;232
	fTellKattaAboutShapeir			;233
	fGiveNote						;234
	fBuyRobe						;235
	fGiveMoneyToDrummer				;236
	fMakeStaff						;237
	fBecomePaladin					;238
	fTellAboutInitiation			;239
	fTellAboutDispelledLeopardLady	;240
	fGiveFeather					;241
	fGiveVineFruit					;242
	fGivePeaceWater					;243
	fGiveHeartGift					;244
	fGetDispelPotions				;245
	fTellAboutJulanar				;246
	fReadBoard						;247
	fAskAboutPeaceMission			;248
	fPoliteToRajahFirst				;249
	fPoliteToRajahSecond			;250
	fSwearOath						;251
)
(define fBringGemToTemple			253)
(define fBeWorthy					254)
(define fBeInBalance				255)
(define fGetVineFruit				256)
(define fSaveMeerbat				257)
(define fGetWater					258)
(define fOrchidGlows				259)

(enum 260
	fGetFeather			;260
	fGetHorn			;261
	fHearStory			;262
	fLoseInitiation 	;263
	fWinInitiation 		;264
	fReturnSpear		;265
	fBreakIntoLaibonHut	;266
)

(define fBeatCroc		292)
(define fBeatAnt		293)
(define fBeatDinosaur	294)

(enum 329
	fGetAnubisEyes			;329
	fOpenLostCityDoor		;330
	fPickDoorLock			;331
	fLubedDoor				;332
	fCalmDemons				;333
	fCastOpenOnDoor			;334
	fBeatFric				;335
	fFindReeshaka			;336
	fDispelReeshaka			;337
	fWinGame				;338
	fEnterMirrorRoom		;339
	fBeatDeWiz				;340
	fKnockedOrb				;341
	fBeatGargoyle			;342
)

;Monster arenas
(define vDinosaur 560)
(define vAnt 565)
(define vGoblin 570)	;unused
;EO: Goblins are mentioned in 550.MSG and the Famous Explorer's Correspondence Course.
(define vApeman 575)
(define vCobra 580)
(define vCroc 585)
(define vDeWorm 590)	;demon worm
(define vLeopardmanEnemy 595)
(define vDemon 845)
(define vGargoyle 855)
(define vMirrorMonster 860)