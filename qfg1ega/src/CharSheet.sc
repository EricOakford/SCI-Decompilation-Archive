;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARSHEET)
(include game.sh)
(use Main)
(use System)

(public
	theCharSheet 0
)

(procedure (ResetLastViewedStats &tmp i)
	(= i 0)
	(while (< i 25)
		(= [oldStats i] [egoStats i])
		(++ i)
	)
)

(procedure (DisplayStatHeading x y &tmp fgColor)
	;Used for displaying the headings: Strength, Experience
	(= fgColor (if (< numColors 16) vBLACK else vBLUE))
	
	(Display &rest 
		100 x y ;CI: NOTE: p_at=100; SCICompanion will not compile if a #define is immediately after a &rest modifier
		p_mode teJustLeft 
		p_font 300 
		p_color fgColor
	)
)

(procedure (DisplayStatBar x y fgColor)
	; Used for displaying Puzzle Points, Experience, and current HP, SP, and MP.

	(Display &rest
		100 x y ;CI: NOTE: p_at=100; SCICompanion will not compile if a #define is immediately after a &rest modifier
		p_mode teJustLeft
		p_font 300
		p_color fgColor
		p_back vWHITE
	)
)

(procedure (GetStatColor skill)
	; If the stat has changed since last viewing, return the appropriate colour.
	
	(return
		(if (== [egoStats skill] [oldStats skill])
			sameColor
		else
			changeColor
		)
	)
)

(procedure (DisplayStat x y skill highlightNew &tmp fg [str 6])
	; Used for displaying the currently skill level
	
	(= fg (if highlightNew (GetStatColor skill) else sameColor))
	(DisplayStatBar
		x y fg
		(Format @str 204 0 [egoStats skill])
		p_width 22
	)
)

(class CharSheet of Object
	(properties
		nsTop 0
		nsLeft 0
		nsBottom southEdge
		nsRight eastEdge
		theWindow 0
		useWindow 0
		showBars 0
	)
	
	(method (init &tmp x)
		(super init:)
		(Load VIEW vCharSheet)
		(if useWindow
			(= theWindow
				(NewWindow nsTop nsLeft nsBottom nsRight {} nwNORMAL nwON_TOP vBLACK vWHITE)
			)
		)
		(= x 26)
		(if (!= heroType FIGHTER)
			;the fighter has a sword, so is a wider cell
			;so the other characters have to be offset accordingly to match.
			(= x (+ x 3))
		)
		;draw the character frame
		(DrawCel 802 2 0 10 23 -1)
		;draw the hero
		(DrawCel 802 0 heroType x 32 -1)
		(if (== heroType MAGE)
			;if it's a MAGE, then also draw the magic spiral.
			(DrawCel 802 1 0 40 52 -1)
		)
		;display all the headings
		(DisplayStatHeading 10 8 204 1)
		;Name :
		(DisplayStatHeading 83 35 204 2)
		;Strength
		(DisplayStatHeading 83 47 204 3)
		(DisplayStatHeading 83 59 204 4)
		(DisplayStatHeading 83 71 204 5)
		(DisplayStatHeading 83 83 204 6)
		(if showBars
			(DisplayStatHeading 10 112 204 7)
			;Puzzle Points
			(DisplayStatHeading 10 124 204 8)
			;Experience
		)
		(DisplayStatHeading 10 148 204 9)
		;Health Points
		(DisplayStatHeading 10 160 204 10)
		;Stamina Points
		(DisplayStatHeading 10 172 204 11)
		;Magic Points
		(DisplayStatHeading 207 28 204 12)
		;Weapon Use
		(DisplayStatHeading 207 40 204 13)
		(DisplayStatHeading 207 52 204 14)
		(DisplayStatHeading 207 64 204 15)
		(DisplayStatHeading 207 76 204 16)
		(DisplayStatHeading 207 88 204 17)
		(DisplayStatHeading 207 100 204 18)
		(DisplayStatHeading 207 112 204 19)
		(self update:)
	)
	
	(method (doit &tmp newEvent newEventType)
		(= newEventType 0)
		(while (not newEventType)
			(GlobalToLocal (= newEvent (Event new:)))
			(= newEventType (newEvent type?))
			(newEvent dispose:)
		)
		(self dispose:)
	)
	
	(method (dispose)
		(ResetLastViewedStats)
		(if theWindow (DisposeWindow theWindow))
		(super dispose:)
		(DisposeScript 204)
	)
	
	(method (update &tmp fgColor [temp1 20])
		(if showBars
			(Display
				@userName 106 260
				p_at 58 8
				p_mode teJustLeft 
				p_font 300
				p_color sameColor
			)
		)
		(DisplayStat 170 35 STR showBars)
		(DisplayStat 170 47 INT showBars)
		(DisplayStat 170 59 AGIL showBars)
		(DisplayStat 170 71 VIT showBars)
		(DisplayStat 170 83 LUCK showBars)
		(if showBars
			(= fgColor
				(if (== score oldScore)
					sameColor
				else
					changeColor
				)
			)
			(= oldScore score)
			(DisplayStatBar
				103 112 fgColor
				(Format @temp1 204 0 score)
				p_width 50
			)
			(DisplayStatBar
				103 124 (GetStatColor EXPER)
				(Format @temp1 204 0 [egoStats EXPER])
				p_width 50
			)
		)
		
		(= fgColor (if showBars (GetStatColor HEALTH) else sameColor))
		(DisplayStatBar
			103 148 fgColor
			(Format @temp1 204 20
				(/ (+ [egoStats HEALTH] 1) 2)
				(/ (+ (MaxHealth) 1) 2)
			)
			p_width 60
		)

		(= fgColor (if showBars (GetStatColor STAMINA) else sameColor))
		(DisplayStatBar
			103 160 fgColor
			(Format @temp1 204 20
				(/ (+ [egoStats STAMINA] 3) 4)
				(/ (+ (MaxStamina) 3) 4)
			)
			p_width 60
		)
		;%d / %d

		(= fgColor (if showBars (GetStatColor MANA) else sameColor))
		(DisplayStatBar
			103 172 fgColor
			(Format @temp1 204 20 
				[egoStats MANA] 
				(MaxMana)
			)
			p_width 60
		)
		;%d / %d
		
		(DisplayStat 288 28 WEAPON showBars)
		(DisplayStat 288 40 PARRY showBars)
		(DisplayStat 288 52 DODGE showBars)
		(DisplayStat 288 64 STEALTH showBars)
		(DisplayStat 288 76 PICK showBars)
		(DisplayStat 288 88 THROW showBars)
		(DisplayStat 288 100 CLIMB showBars)
		(DisplayStat 288 112 MAGIC showBars)
	)
)

(instance theCharSheet of CharSheet
	(properties)
)
