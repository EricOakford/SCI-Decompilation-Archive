;;; Sierra Script 1.0 - (do not remove this comment)
(include system.sh) (include sci2.sh)

; Icons
(enum
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_ZIPPER
	ICON_SKIP
	ICON_ITEM
	ICON_INVENTORY	
	ICON_CONTROL
	ICON_HELP
)

; Video Tape states
(enum
	tapeBLANK
	tapeERASED
	tapeMICHELLE
	tapeLANA
	tapeCHICHI
)

;Hard Disk Cafe membership tape states
(enum
	HDCnotYet
	HDCgotTape
	HDCbribed
	HDChacked
	HDCdone
)

;DataMan states
(enum
	datamanEMPTY
	datamanBIAZ
	datamanHAMMER
)

(define DEBUG 10)
(define DISPOSE 11)
(define COLORINIT 12)
(define ABOUT 15)
(define LL5INIT 16)
(define LLROOM 18)
(define LLINV 19)
(define DIALER 20)
(define DOOR 21)
(define CHARGER 22)
(define LARRY 23)
(define PATTI 24)
(define rgHollywood 40)

; Larry's Inventory
(enum
	iCamcorder			;0
	iCharger			;1
	iTapeA				;2
	iTapeB				;3
	iTapeC				;4
	iMichelleResume		;5
	iNapkin				;6
	iGoldCard			;7
	iBoardingPass		;8
	iMagazine			;9
	iChange				;10
	iDayTrotter			;11
	iMoney				;12
	iCreditCards		;13
	iMembershipTape		;14
	iLanaResume			;15
	iMatchbook			;16
	iSilverDollar		;17
	iRollerskates		;18
	iChiChiResume		;19
	iPulliamCard		;20
	iGreenCard			;21
	iDoily				;22
	
)

;Patti's Inventory
(enum
	iDataMan				;0
	iBiazPak				;1
	iHammerPak				;2
	iBiazFax				;3
	iChampagne				;4
	iGoldRecord				;5
	iCassette				;6
	iHammerFax				;7
	iLetterOpener			;8
	iDeskKey				;9
	iEvidenceFolder			;10
	iPhotocopiedEvidence	;11
	iReelToReelTape			;12
	iHooterShooter			;13
)

; Event Flags
(enum
	fIsVGA							;0
	fLimoHere						;1
	fMetMichelle					;2
	fMetLana						;3
	fDentistClosed					;4
	fFlag5							;5
	fRentedSkates					;6
	fAfterCoffee					;7
	fFlightAvailable				;8
	fAfterNewYork					;9
	fAfterAtlanticCity				;10
	fAfterMiami						;11
	fCalledForGreenCard				;12
	fStoleChange					;13
	fGetIntoHDC						;14
	fFlag15							;15
	fHardDiskCafeDone				;16
	fKnowBiazAddress				;17
	fCalledDentist					;18
	fEnteredStudio					;19
	fBiazDrunk						;20
	fBlewGig						;21
	fRecordOnPlayer					;22
	fFoundDeskKey					;23
	fDeskUnlocked					;24
	fLookedAtDesk					;25
	fEnteredKRAP					;26
	fCoveredInToner					;27
	fAfterHammer					;28
	fAfterBiaz						;29
	fGotChampagne					;30
	fGotDayTrotter					;31
	fNowBoarding					;32
	fFakedInjury					;33
	fGotChange						;34
	fWearingHooterShooter			;35
	fPlayedSlots					;36
	fEnteringRightArenaDoor			;37
	fLimoAvailable					;38
	fFlag39							;39
	fMichelleAteCherry				;40
	fSeeVibratorDemonstration		;41
	fSeeBraDemonstration			;42
	fSeeFartDemonstration			;43
	fBeenIn440						;44
	fBiazFailed						;45
	fBoardingPassReady				;46
	fSkippedEnding					;47
	fBiazReady						;48
	fTrampCasinoDone				;49
	fFlag50							;50
	fGaveGreenCard					;51
	fPutMagazineOnTable				;52
)
(enum 65
	fPlugCharger					;65
	fPlugCamcorder					;66
	fUnplugCharger					;67
	fReadMagazine					;68
	fFoundNapkin					;69
	fFindMatchbook					;70
	fFindDentistCard				;71
	fTurnOnCamera					;72
	fPutTapeInCamera				;73
	fSearchDayTrotter				;74
	fPutPakInDataMan				;75
	fLookAtHooterShooter			;76
	fReadDentishCard				;77
	fReadHammerDataPak				;78
	fGetCamcorder					;79
	fFlag80							;80
	fLookAtBronzeAward				;81
	fGetCoffee						;82
	fSterlizedHands					;83
	fPlayed8Tracks					;84
	fGotCharger						;85
	fGotTapes						;86
	fErasedTapes					;87
	fFlag88							;88
	fGotResumes						;89
	fFlag90							;90
	fLearnNewYorkLimoNumber			;91
	fShowedCardToCamera				;92
	fLearnMiamiLimoNumber			;93
	fLearnAtlanticCityLimoNumber	;94
	fPattiTakesRide					;95
	fLearnGreenCardNumber			;96
	fInsertBoardingPass				;97
	fBraPoints						;98
	fVibratorPoints					;99
	fGotHooterShooter				;100
	fFartPoints						;101
	fGotDataMan						;102
	fGotBiazFax						;103
	fGotHammerFax					;104
	fTookPissIn500					;105
	fTapePrinted					;106
	fHackedMembershipTape			;107
	fBribedMaitreD					;108
	fGotMembershipTape				;109
	fCallForLimoInNewYork 			;110
	fInsertedMembershipTape			;111
	fGiveMichelleDayTrotter			;112
	fGiveMichelleCreditCards		;113
	fGiveMichelleMoney				;114
	fMichelleGetsItOn				;115
	fAfterMichelle					;116
	fRecordedMichelle				;117
	fShowedBiazPakToGuard			;118
	fShowedBiazFaxToGuard			;119
	fFindBiazRoomOnBoard			;120
	fGetGoldRecord					;121
	fPlayRecord33Forward			;122
	fPlayRecord33Reverse			;123
	fPlayRecord78Forward			;124
	fPlayRecord78Reverse			;125
	fAfterRecordingSession			;126
	fGotBiazDrunk					;127
)
(enum 130
	fGotCassette					;130
	fTalkedToChangeGirl				;131
	fDoormanCallsForLimo			;132
	fPlayedVideoPoker				;133
	fPaidForMudWrestling			;134
	fLookAtJennifer					;135
	fAfterLana						;136
	fRecordedLana					;137
	fTalkedToLana					;138
	fPutOnSkates					;139
	fAskedLanaOut					;140
	fCameraForSkates				;141
	fReturnedSkates					;142
	fSilverForSkates				;143
	fOpenedDesk						;144
	fGotEvidence					;145
	fSearchEvidenceFolder			;146
	fUnlockedDesk					;147
	fGotDeskKey						;148
	fGotLetterOpener				;149
	fEnteredKRAPOffice				;150
	fPhotocopiedEvidence			;151
	fFlag152						;152
	fWearingRapClothes				;153
	fHeardScrew						;154
	fTurnedOnKRAPRecorder			;155
	fRecordedReelToReelTape			;156
	fUsedMike						;157
	fGotReelToReelTape				;158
	fWoreDoily						;159
	fFlag160						;160
	fTookDoily						;161
	fEmergencyAppointment			;162
	fPhoneAppointment				;163
	fPassedDentishQuiz				;164
	fAfterChiChi					;165
	fSeeChiChiBoobs					;166
	fRecordedChiChi					;167
)

;Cities
(enum
	LOS_ANGELES
	NEW_YORK
	ATLANTIC_CITY
	MIAMI
)

; Verbs
(enum 10
	verbZipper		;10
)
