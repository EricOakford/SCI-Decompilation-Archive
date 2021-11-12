;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)
;(include colors.sh)

;Script defines
(define AUTODOOR	301)
(define DSELECTOR	975)	;split from INTRFACE.SC
;Locales
(define regGun		150)
(define regFieldKit	153)
(define regJet		154)
(define regCove		155)
(define regOffice	156)
(define DEBUG		801)

;Regions
(define regSewer 205)

;Inventory items
(enum
	iHandGun			;0
	iAmmoClips			;1
	iKeyRing			;2
	iUnmarkedCarKeys	;3
	iMoneyClip			;4
	iThankYouLetter		;5
	iDeathThreat		;6
	iWallet				;7
	iHandcuffs			;8
	iWireClippers		;9
	iFieldKit			;10
	iFlower				;11
	iNewMugShot			;12
	iHitList			;13
	iKnife				;14
	iEarProtectors		;15
	iPlaneTicket		;16
	iPlasterCast		;17
	iLostBadge			;18
	iThumbprint			;19
	iBullets			;20
	iEmptyHolster		;21
	iFingerprint		;22
	iOldMugShot			;23
	iEnvelopeCorner		;24
	iEnvelope			;25
	iJailClothes		;26
	iMoteKey			;27
	iVialOfBlood		;28
	iLipstick			;29
	iWalkieTalkie		;30
	iJailerRevolver		;31
	iGasMask			;32
	iBombInstructions	;33
	iCarRegistration	;34
	iColbyCard			;35
	iMarieDoorNote		;36
	iLPDBusinessCard	;37
)

;door states
(enum
	doorClosed
	doorClosing
	doorOpen
	doorOpening
)

;Game phases
(define phaseMALL 3)
(define phaseCOVE 5)

;Cars
(define carWork 13)
(define carPersonal 33)

;Gunfire states
(enum 1
	gunPERMITTED		;1	;deadly force may be needed
	gunUSELESS			;2	;just wastes ammo and prints a messsage
	gunPROHIBITED		;3	;institutionalized, ending the game
)

;Date aftermath states
(enum
	dateNOCALL
	dateNODATE
	dateKISSED
	dateNOKISS
	dateNOEAT
	dateWALKEDOUT
)

;Event flags
(enum 1
	fCanGetWarrant				;1
	fCanCallForBackup	 		;2
	fGotTank					;3
	fGotWetSuit					;4
	fGotFins					;5
	fGotWeightBelt				;6
	fGotBuoyancyVest			;7
	fGotScubaMask				;8
	fCoronerHasWoody			;9
	fDocBookingEvidence			;10
	fKitBinOpen					;11
	fEgoDeskLocked				;12 ;was fEgoDeskUnlocked
	fGotMotelWarrant			;13
	fSWATAtHotel				;14
	fFlag15						;15
	fFlag16						;16
)
(define fKidnappingReported 27)
(define fReportedMarieMissingToCaptain 31) ;was fFlag31
(define fVisitedHomicideOffice 32)
(define fFoundDiverCard 33)
(define fTriedToGetTicketToHouston 34)
(define fWomanTalkToHaines 35)
(define fHoustonAuthorized 36)
(define fSteeltonAuthorized 37)
(define fKeithFollows 40)
(define fSmudgedPrints 41)
(define fFlag45 45)
(enum 49
	fGotToiletGun					;49
	fGotEmptyHolster				;50
	fAskedWomanAboutCar				;51
	fFlag52							;52
	fFlag53							;53
	fCalledCoroner					;54
	fFlag55							;55
	fLearnedAboutAddiction			;56
	fSteveTellsAboutLloyd			;57
	fFirstFindDivingCard			;58
	fOpenLocker						;59
	fGetGun							;60
	fGetHandcuffs					;61
	fGetAmmoClips					;62
	fGetFieldKit					;63
	fReadSubpoena					;64
	fReadEvidenceReport			 	;65
	fGetEarProtectors				;66
	fAlignGunSights					;67
	fGetMoreAmmo					;68
	fAskedToSeeWitness				;69
	fInterrogatedWitness			;70
	fSearchedCoveGround				;71
	fTookPictureCove				;72
	fReadJailClothesTag				;73
	fGotCorrectScubaTank			;74
	fFlag75							;75
	fFlag76							;76
	fReadJailerVIN					;77
	fWaitedToCrossNorth				;78
	fWaitedToCrossSouth				;79
	fBoughtFlower					;80
)
(define fLookedAtPassengerList 82)
(define fAgentRecognizedBains 83)
(define fReadCarRentals 84)
(define fLloydInRehab 85)
(define fLeanredBainsWasAtMotel 88)
(define fCheckMarieHandwriting 90)
(define fColbyPhoneTap 94)
(define fWarnedColby 95)
(define fGateGuardShownBadge 98)
(define fFindManhole 100)
(define fOpenManhole 101)
(define fGetUnmarkedCarKey 104)
(define fGetWallet 105)
(define fGetThankYouLetter 106)
(define fFoundJailerHand 108)
(define fFoundJailerBody 109)
(define fGetJailerCarDescription 110)
(define fRadioInCoveShooting 111)
(define fBeenAtMall 112)
(define fHaveMotelBlood 113)
(define fLockedUpGunAtJail 115)
(define fStolenCarTowed 118)
(define fFoundWomanStolenCar 119)
(enum 122
	fFoundMarie							;122
	fReadBulletinBoard					;123
	fLearnPasswords						;124
	fGotPoints							;125
	fToldKeithAboutRevolver				;126
	fBookedRevolver						;127
	fBookedFingerprint					;128
	fBppkedThumbprint					;129
	fBookedKnife						;130
	fFlag131							;131 ;btst in room 4 says this is river blood. duplicate of 144?
	fBookedPlasterCast					;132
	fBookedBullets						;133
	fBookedJailClothes					;134
	fBookedHitList						;135
	fBookedColbyCard					;136
	fBookedEnvelope						;137
	fBookedEnvelopeCorner				;138
	fBookedLostBadge					;139
	fFlag140							;140 ;is tested and cleared when entering Burt's Park, but is never set
	fFlag141							;141
	fKeithWaitsAtJail					;142
	fHaveTrunkBlood						;143
	fHaveCoveBlood						;144
	fBookedHolster						;145
	fBookedCoveBlood					;146
	fBookedTrunkBlood					;147
	fBookedMotelBlood					;148
	fFoundWoodyInTrunk					;149
	fMarshallLeftBathroom				;150
	fMarshallAtSink						;151
	fBeenAtMallCrimeScene				;152
	fFlag153							;153
	fFlag154							;154
	fGotTicketToSteelton 				;155
	fToldKeithAboutMallScene			;156
	fFlag157							;157
	fRadioedInRentalCar					;158
	fRadioedInStolenCarPlate			;159
	fSaveMarie							;160
	fCalmMarie							;161
	fFlag162							;162
	fBuckledUp							;163
	fFlag164							;164
	fDroveToMotel						;165
	fTriedToGoToHouston					;166
	fGameIsRestarting					;167
)

(define SaveSize 1200)