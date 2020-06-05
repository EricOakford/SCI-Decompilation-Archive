;;; Sierra Script 1.0 - (do not remove this comment)
;charsave.sc   Quest for Glory...save character stats.

(script# CHARSAVE) ;CHARSAVE = 601
(include game.sh)
(use Main)
(use Intrface)
(use File)
(use Game)
(use User)
(use System)

(public
	CharSave 0
)
;; Bits in svMiscEquip
(define  SWORD_BIT   1)
(define  CHAIN_BIT   2)
(define  PICK_BIT    4)
(define  TOOL_BIT    8)
(define  MIRROR_BIT  16)
(define  BABA_BIT    32)          
(define  SCORE_BIT   64)

(define  EXTRA_DATA  18)   ; Data items other than stats and name
(define  CHECK_DATA  10)   ; Data items that are in check sums

(local
;; local data for saving hero stats for next game
                        ;;;;;;;;;;;;;;;;;;start;;;;;;;;;;;;;;;;;;
	statsKey =  $53		;;;;;;;;order dependent variables;;;;;;;;
	svCharType			;;;;;;;;order dependent variables;;;;;;;;
	svHighGold			;;;;;;;;order dependent variables;;;;;;;;
	svLowGold			;;;;;;;;order dependent variables;;;;;;;;
	svScore				;;;;;;;;order dependent variables;;;;;;;;
	svMiscEquip			  ;;;;;;order dependent variables;;;;;;;;
	[codedStats NUMSTATS] ;;;;;;order dependent variables;;;;;;;;
	svDaggers			  ;;;;;;order dependent variables;;;;;;;;
	svHealing			;;;;;;;;order dependent variables;;;;;;;;
	svMana				;;;;;;;;order dependent variables;;;;;;;;
	svStamina			;;;;;;;;order dependent variables;;;;;;;;
	svGhostOil			;;;;;;;;order dependent variables;;;;;;;;
	bogus0 =  $79		;;;;;;;;order dependent variables;;;;;;;;
	bogus1 =  $86		;;;;;;;;order dependent variables;;;;;;;;
	checkSum1			;;;;;;;;order dependent variables;;;;;;;;
	checkSum2			;;;;;;;;order dependent variables;;;;;;;;
	bogus2 =  $43		;;;;;;;;order dependent variables;;;;;;;;
	bogus3 =  $88		;;;;;;;;order dependent variables;;;;;;;;
	bogus4 =  $ad		;;;;;;;;order dependent variables;;;;;;;;
	bogus5 =  $f0		;;;;;;;;order dependent variables;;;;;;;;
	checkSumKey =  $ce	;;;;;;;;order dependent variables;;;;;;;;
						;;;;;;;;;;;;;;;;;;;end;;;;;;;;;;;;;;;;;;;
	[check 2]
	[YNSTR 5]
	[heroFileName 16]
	[bigStr 400]
	hasSaved			;TRUE if hero saved
	[str 40]
)

(enum          ;states of saveHero Script
   askSave
   getInfoFileName
   getInfoFileName2
   openFile
   writeHeroInfo
   writeComplete
   tryAgain
   badAnswer
   saveDone
)

(instance CharSave of Room
	(properties
		picture pBlueSkyForCarpet
		style IRISOUT
	)
	
	(method (init)
		(StatusLine code: endStatus enable:)
		(super init: &rest)
		(cSound stop:)
		
		; don't let'm control anything!
		(User canControl: FALSE canInput: FALSE)
		
		(self setScript: saveHero)
	)
	
	(method (dispose)
		(StatusLine code: dftStatusCode)
		(super dispose:)
	)
)

(instance heroinfo of File
	(properties
		name "glory1.sav"
	)
)

(instance saveHero of Script
	
	(method (changeState newState &tmp whichSkill oldGold)
		(switch (= state newState)
			(askSave
				(Format @heroFileName 601 0)
				;a:glory1.sav
				(if (>= score 500)
					(HighPrint 601 1)
					;CONGRATULATIONS!!  You have successfully completed "Quest for Glory I: So You Want To Be A Hero"
					;with the maximum possible score, 500 of 500!!
					;We welcome you to the ranks of the few, the proud, the True Heroes!
				else
					(HighPrint (Format @bigStr 601 2 score))
					;Congratulations!  You have successfully completed "Quest for Glory I:  So You Want To Be A Hero."
					;Your final score was %d of 500 possible Puzzle Points.
				)
				(HighPrint 601 3)
				;If you have not already done so, we encourage you to play "Quest for Glory I" again with the other two
				;character types; many of the puzzles are different, or have alternate solutions.
				(HighPrint 601 4)
				;In the meantime, you are already a winner!  Please insert a writeable disk in your floppy drive to save your winning Hero for use in
				;"Quest for Glory II:  Trial By Fire."
				(self cue:)
			)
			(getInfoFileName
				(= cycles 2)
				)
			(getInfoFileName2
				(if
					(GetInput
						@heroFileName
						30
						{Disk file in which to save your Hero.}
					)
					(heroinfo name: @heroFileName)
					(= cycles 2)
				else
					(self changeState: tryAgain)
				)
			)
			(openFile
				(if (heroinfo open: fTrunc)
					(heroinfo close:)
					(= seconds 2)
				else
					(HighPrint (Format @bigStr 601 5 (heroinfo name?)))
					;Could not create file -- %s.
					(self changeState: tryAgain)
				)
			)
			(writeHeroInfo
				(if (not (heroinfo open: fAppend))
					(self changeState: tryAgain)
					(return)
				)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
				(= whichSkill 0)
				(while (< whichSkill NUMSTATS)
					(= [codedStats whichSkill] [egoStats whichSkill])
					(++ whichSkill)
				)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
				(= oldGold (+ [invNum iGold] (/ [invNum iSilver] 10)))
				(= svCharType heroType)
				(= svHighGold (/ oldGold 100))
				(= svLowGold (mod oldGold 100))
				(= svScore score)
				(= svMiscEquip 0)
				(if (ego has: iSword) 		(= svMiscEquip (| svMiscEquip SWORD_BIT)))
				(if (ego has: iChainmail)	(= svMiscEquip (| svMiscEquip CHAIN_BIT)))
				(if (ego has: iLockPick) 			(= svMiscEquip (| svMiscEquip PICK_BIT)))
				(if (ego has: iThiefKit) 		(= svMiscEquip (| svMiscEquip TOOL_BIT)))
				(if (ego has: iMagicMirror) 			(= svMiscEquip (| svMiscEquip MIRROR_BIT)))
				(if (Btst fBabaFrog)		 	(= svMiscEquip (| svMiscEquip BABA_BIT)))
				(if (< 255 score) 					(= svMiscEquip (| svMiscEquip SCORE_BIT)))
				(= svDaggers [invNum iDagger])
				(= svHealing [invNum iHealingPotion])
				(= svMana [invNum iManaPotion])
				(= svStamina [invNum iStaminaPotion])
				(= svGhostOil [invNum iGhostOil])
				(= checkSum1 checkSumKey)
				(= whichSkill 0)
				(while (< whichSkill (+ NUMSTATS CHECK_DATA))
					(= [statsKey (+ whichSkill 1)]
						(& [statsKey (+ whichSkill 1)] $007f)
					)
					(= checkSum1 (+ checkSum1 [statsKey (+ whichSkill 1)]))
					(= whichSkill (+ whichSkill 2))
				)
				(= checkSum2 0)
				(= whichSkill 1)
				(while (< whichSkill 35)
					(= [statsKey (+ whichSkill 1)]
						(& [statsKey (+ whichSkill 1)] $007f)
					)
					(= checkSum2 (+ checkSum2 [statsKey (+ whichSkill 1)]))
					(= whichSkill (+ whichSkill 2))
				)
				(= checkSum1 (& checkSum1 $007f))
				(= checkSum2 (& checkSum2 $007f))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
				(= whichSkill 0)
				(while (< whichSkill (+ NUMSTATS EXTRA_DATA))
					(= [statsKey (+ whichSkill 1)]
						(& [statsKey (+ whichSkill 1)] $007f)
					)
					(= [statsKey (+ whichSkill 1)]
						(^ [statsKey (+ whichSkill 1)] [statsKey whichSkill])
					)
					(++ whichSkill)
				)
				(heroinfo write: @userName)
				(heroinfo write: {\n})
				(= whichSkill 1)
				(while (< whichSkill (+ NUMSTATS EXTRA_DATA 1))
					(Format @bigStr 601 6 [statsKey whichSkill])
					;%2x
					(heroinfo write: @bigStr)
					(++ whichSkill)
				)
				(heroinfo write: {\n})
				(heroinfo close:)
				(= seconds 2)
			)
			(writeComplete
				(HighPrint 601 7)
				;The save character file has been created.  Save this disk for use with "Quest for Glory II:  Trial By Fire" from Sierra On-Line.
				(= hasSaved TRUE)
				(= cycles 2)
			)
			(tryAgain
				(Format @YNSTR 601 8)
				;n
				(if
					(GetInput @YNSTR 2
						{If you wish to try saving your character again, type "y", then ENTER.__Otherwise type "n", then ENTER.}
					)
					(if (StrCmp @YNSTR {y})
						(self changeState: saveDone)
					else
						(= bogus0 121)
						(= bogus1 134)
						(= bogus2 67)
						(= bogus3 136)
						(= bogus4 173)
						(= bogus5 240)
						(self changeState: getInfoFileName)
					)
				else
					(= cycles 2)
				)
			)
			(badAnswer
				(HighPrint 601 9)
				;Please answer "y" or "n".
				(self changeState: tryAgain)
			)
			(saveDone
				(HighPrint 601 10)
				;Thank you for playing "Quest for Glory I:  So You Want To Be A Hero,"
				;and congratulations again on winning.  We'll see you again soon in "Quest for Glory II:  Trial By Fire."
				(= quit TRUE)
			)
		)
	)
)

(instance endStatus of Code
	
	(method (doit strg)
		(Format strg 601 11 score)
		;   Wow!  You're Really A Hero!  [score %d of 500]
	)
)
