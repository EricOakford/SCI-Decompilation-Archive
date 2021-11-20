;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_INIT)
(include game.sh)
(use Main)
(use Plane)
(use Talker)
(use User)
(use Actor)
(use System)

(public
	glryInit 0
)

(procedure (SpeedTest &tmp fred doneTime)
	((= fred (View new:))
		view: 9999
		loop: 0
		cel: 0
		x: 0
		y: 100
		init:
	)
	(= doneTime (GetTime))
	(while (< (fred x?) 320)
		(UpdateScreenItem
			(fred x: (+ (fred x?) 4) yourself:)
		)
		(FrameOut)
	)
	(= combatSpeed (- (GetTime) doneTime))
	(fred dispose:)
)

(instance glryInit of Code
	(method (init &tmp [temp0 2])
		(RemapColors 5 66 20)
		(User
			alterEgo: ego
			canControl: FALSE
			canInput: FALSE
			mapKeyToDir: FALSE
		)
		(ego setSpeed: 0)
		(= systemPlane Plane)
		((= narrator Narrator)
			back: 255
			font: 300
			y: 120
		)
		(Bset fVoiceOn)
		(|= msgType CD_MSG)
		(theGame masterVolume: 15)
		(= useSortedFeatures TRUE)
		(= numVoices (DoSound SndNumVoices))
		(= possibleScore 500)
		(= userFont 300)
		(= smallFont 999)
		(= bigFont 300)
		(= stamCounter 20)
		(= healCounter 15)
		(= manaCounter 20)
		(= healFormula (Random 1 26))
		(= cureFormula (Random 1 26))
		(= rehydrateFormula (Random 1 26))
		(if (HaveMouse)
			(= eatMice 6)
			(theGame setCursor: normalCursor TRUE)
		else
			(theGame setCursor: normalCursor TRUE 304 174)
		)
		(= [egoStats HEALTH] (ego maxHealth:))
		(= [egoStats STAMINA] (ego maxStamina:))
		(= [egoStats MANA] (ego maxMana:))
		(= paladinStat TRUE)
		(Bset fAutoSave)
		(SpeedTest)
		((inventory at: iShield) state: 1)
		((inventory at: iSword) state: 1)
		(MaxStat HEALTH)
		(MaxStat STAMINA)
		(MaxStat MANA)
		(theGame newRoom: 4)
	)
)
