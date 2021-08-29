;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARSHEET)
(include game.sh)
(use Main)
(use System)

(public
	theCharSheet 0
)

(procedure (SaveStats &tmp i)
	(for ((= i 0)) (< i NUMSTATS) ((++ i))
		(= [oldStats i] [egoStats i])
	)
)

(procedure (ShowTitle x y &tmp fgColor)
	;Used for displaying the headings: Strength, Experience
	(= fgColor (if (< numColors 16) vBLACK else vBLUE))
	
	(Display &rest 
		100 x y ;CI: NOTE: p_at=100; SCICompanion will not compile if a #define is immediately after a &rest modifier
		p_mode teJustLeft 
		p_font 300 
		p_color fgColor
	)
)

(procedure (ShowValue x y fgColor)
	; Used for displaying Puzzle Points, Experience, and current HP, SP, and MP.

	(Display &rest
		100 x y ;CI: NOTE: p_at=100; SCICompanion will not compile if a #define is immediately after a &rest modifier
		p_mode teJustLeft
		p_font 300
		p_color fgColor
		p_back vWHITE
	)
)

(procedure (SkillColor statNum)
	; If the stat has changed since last viewing, return the appropriate colour.
	
	(return
		(if (== [egoStats statNum] [oldStats statNum])
			sameColor
		else
			changeColor
		)
	)
)

(procedure (ShowSkill x y statNum varColor &tmp fg [str 6])
	; Used for displaying the current skill level
	
	(= fg (if varColor (SkillColor statNum) else sameColor))
	(ShowValue
		x y fg
		(Format @str 204 0 [egoStats statNum])
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
				(NewWindow nsTop nsLeft nsBottom nsRight {} 0 -1 vBLACK vWHITE)
			)
		)
		(= x 26)
		(if (!= heroType FIGHTER)
			;the fighter has a sword, so is a wider cell
			;so the other characters have to be offset accordingly to match.
			(+= x 3)
		)
		;draw the character frame
		(DrawCel 802 2 0 10 23 -1)
		;draw the hero
		(DrawCel 802 0 heroType x 32 -1)
		(if (== heroType MAGIC_USER)
			;if it's a MAGIC_USER, then also draw the magic spiral.
			(DrawCel 802 1 0 40 52 -1)
		)
		;display all the headings
		(ShowTitle 10 8 204 1)
		;Name :
		(ShowTitle 83 35 204 2)
		;Strength
		(ShowTitle 83 47 204 3)
		(ShowTitle 83 59 204 4)
		(ShowTitle 83 71 204 5)
		(ShowTitle 83 83 204 6)
		(if showBars
			(ShowTitle 10 112 204 7)
			;Puzzle Points
			(ShowTitle 10 124 204 8)
			;Experience
		)
		(ShowTitle 10 148 204 9)
		;Health Points
		(ShowTitle 10 160 204 10)
		;Stamina Points
		(ShowTitle 10 172 204 11)
		;Magic Points
		(ShowTitle 207 28 204 12)
		;Weapon Use
		(ShowTitle 207 40 204 13)
		(ShowTitle 207 52 204 14)
		(ShowTitle 207 64 204 15)
		(ShowTitle 207 76 204 16)
		(ShowTitle 207 88 204 17)
		(ShowTitle 207 100 204 18)
		(ShowTitle 207 112 204 19)
		(self update:)
	)
	
	(method (doit &tmp event ret)
		(= ret 0)
		(while (not ret)
			(GlobalToLocal (= event (Event new:)))
			(= ret (event type?))
			(event dispose:)
		)
		(self dispose:)
	)
	
	(method (dispose)
		(SaveStats)
		(if theWindow
			(DisposeWindow theWindow)
		)
		(super dispose:)
		(DisposeScript 204)
	)
	
	(method (update &tmp fgColor [str 20])
		(if showBars
			(Display
				@userName 106 260
				p_at 58 8
				p_mode teJustLeft 
				p_font 300
				p_color sameColor
			)
		)
		(ShowSkill 170 35 STR showBars)
		(ShowSkill 170 47 INT showBars)
		(ShowSkill 170 59 AGIL showBars)
		(ShowSkill 170 71 VIT showBars)
		(ShowSkill 170 83 LUCK showBars)
		(if showBars
			(= fgColor
				(if (== score oldScore)
					sameColor
				else
					changeColor
				)
			)
			(= oldScore score)
			(ShowValue
				103 112 fgColor
				(Format @str 204 0 score)
				p_width 50
			)
			(ShowValue
				103 124 (SkillColor EXPER)
				(Format @str 204 0 [egoStats EXPER])
				p_width 50
			)
		)
		
		(= fgColor (if showBars (SkillColor HEALTH) else sameColor))
		(ShowValue
			103 148 fgColor
			(Format @str 204 20
				(/ (+ [egoStats HEALTH] 1) 2)
				(/ (+ (MaxHealth) 1) 2)
			)
			p_width 60
		)

		(= fgColor (if showBars (SkillColor STAMINA) else sameColor))
		(ShowValue
			103 160 fgColor
			(Format @str 204 20
				(/ (+ [egoStats STAMINA] 3) 4)
				(/ (+ (MaxStamina) 3) 4)
			)
			p_width 60
		)
		;%d / %d

		(= fgColor (if showBars (SkillColor MANA) else sameColor))
		(ShowValue
			103 172 fgColor
			(Format @str 204 20 
				[egoStats MANA] 
				(MaxMana)
			)
			p_width 60
		)
		;%d / %d
		
		(ShowSkill 288 28 WEAPON showBars)
		(ShowSkill 288 40 PARRY showBars)
		(ShowSkill 288 52 DODGE showBars)
		(ShowSkill 288 64 STEALTH showBars)
		(ShowSkill 288 76 PICK showBars)
		(ShowSkill 288 88 THROW showBars)
		(ShowSkill 288 100 CLIMB showBars)
		(ShowSkill 288 112 MAGIC showBars)
	)
)

(instance theCharSheet of CharSheet)
