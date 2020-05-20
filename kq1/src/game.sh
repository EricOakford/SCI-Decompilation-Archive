;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

;Inventory items
(enum
	iDagger			;0
	iChest			;1
	iCarrot			;2
	iKey			;3
	iNote			;4
	iMagicRing		;5
	iFourLeafClover	;6
	iCeramicBowl	;7
	iWaterBucket	;8
	iPebbles		;9
	iSlingshot		;10
	iPouch			;11
	iSceptre		;12
	iCheese			;13
	iMagicMirror	;14
	iGoldEgg		;15
	iMagicShield	;16
	iFiddle			;17
	iWalnut			;18
	iMushroom		;19
	iBeans			;20
)

;Who's carrying Graham through the sky
(enum 1
	carrierCONDOR 	;1
	carrierWITCH	;2
)

;Regions
(enum 600
	GOAT		;600
	TROLL		;601
	MENACE		;602
	WATER		;603
	GARDEN		;604
	MOAT		;605
	BEANS		;606
	STALK		;607
	RIVER		;608
	CLIMB		;609
	CLOUDS		;610
	BIRD		;611
	WATER_42	;612 ;room 42 has both safe and deadly water, so a special region is needed for it
	FALLING		;613
)
(define HALO 616)

;Menaces
(define SORCERER 800)
(define OGRE 803)
(define DWARF 804)

;Global stuff
(define KQ_WINDOW 778)

;Event flags
(enum
	fLittleEgo					;0	;for Graham's small views, which are used in Cloudland
	fInvisible					;1
	fGoatFollows				;2
	fUnused3					;3
	fTrollPaid					;4
	fTrollDead					;5
	fGnomeAsks					;6
	fVisitedLeprechaunThrone	;7
	fVisitedWoodcutter			;8
	fWaterInBucket				;9
	fAteCarrot					;10
	fAttackedElf				;11
	fWitchHasEgo				;12
	fTrollBlocksBridge			;13
	fSceptreOnThrone			;14
	fCupboardOpen				;15
	fWitchIsHome				;16
	fWearingRing				;17
	fEgoInBucket				;18
	fClimbingRope				;19
	fRopeLowered				;20
	fBucketLowered				;21
	fMetGodmother				;22
	fGoatPenOpen				;23
	fGotBucketFromWell			;24
	fCutRope					;25
	fOpenedChest				;26
	fMetGnome					;27
	fMuggedInCave				;28
	fFlag29						;29
	fFlag30						;30	;related in some way to the dwarf appearing in the mountain cave
	fDwarfInCave				;31
	fGaveRatSomething			;32
	fLepsGone					;33
	fAteStew					;34
	fAteBeans					;35
	fGoatFriend					;36
	fGnomeAttention				;37
	fGiantSleeps				;38
	fMountainDoorOpen			;39
	fInCartoon	 				;40 ;when not playing the game proper
	fFlag41						;41
	fUnused42					;42
	fPushingWitch				;43
	fClimbingHill				;44
	fUnused45					;45
	fFallFromTree				;46
	fBucketFloats				;47
	fClimbing					;48
	fLookedAtGarden				;49
	fLookedAtGardenBorder		;50
	fHaloExpired				;51
	fUnused52					;52
	fUnused53					;53
	fUnused54					;54
	fUnused55					;55
	fUnused56					;56
	fUnused57					;57
	fUnused58					;58
	fUnused59					;59
	fSawPouchInStump			;60
	fGotPouch					;61
	fOpenedPouch				;62
	fFilledBucket				;63
	fPickedCarrot				;64
	fGotRing					;65
	fGotWalnut					;66
	fOpenedWalnut				;67
	fUnlockedMountainDoor		;68
	fGotMushroom				;69
	fGotClover					;70
	fGotShield					;71
	fGotSceptre					;72
	fClimbedDownWell			;73
	fKilledWitch				;74
	fFoundCheese				;75
	fGotCheese					;76
	fGotNote					;77
	fReadNote					;78
	fAteWitchHouse				;79
	fOfferedGoatCarrot			;80
	fGotBowl					;81
	fDragonDoused				;82
	fDragonDead					;83
	fGotMirror					;84
	fGotSlingshot				;85
	fGiantDead					;86
	fGotChest					;87
	fGiantTired					;88
	fMovedRock					;89
	fTookDagger					;90
	fGotPebbles					;91
	fGotKey						;92
	fReadBowl					;93
	fGotGoldEgg					;94
	fGotBucket					;95
	fClimbedDownWell2			;96 ;set at same time as flag 73. Why the redundant flags?
	fDiveUnderwater				;97
	fEnteredDragonDen			;98
	fEnteredRockCave			;99
	fGotRideFromBird			;100
	fClimbedTree				;101
	fGaveRatCheese				;102
	fLepsDance					;103
	fAteMushroom				;104
	fExitedLepCave				;105
	fGoatKilledTroll			;106
	fUnused107					;107
	fGotBeans					;108
	fPlantedBeanstalk			;109
	fClimbedToCloudland			;110
	fEndGame					;111
	fBowlHasStew				;112
	fGaveStewToWoodCutter		;113
	fGotFiddle 			;114
)

;Required memory sizes in bytes
(define InvSize 1550)