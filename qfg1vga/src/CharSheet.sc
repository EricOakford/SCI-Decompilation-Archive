;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARSHEET)
(include game.sh)
(use Main)
(use Procs)
(use User)
(use Actor)
(use System)

(public
	theCharSheet 0
)

(local
	[local0 2] = [10000]
	oldSortedFeatures
	oldCast
	oldFeatures
	oldMouseDownHandler
	oldKeyDownHandler
	userInput
	userControls
)

(procedure (ShowSkill xPlace yPlace statNum varColor &tmp bgColor fgColor theWidth [str 6])
	(if (!= [egoStats statNum] [oldStats statNum])
		(= bgColor 54)
		(= fgColor 57)
	else
		(= bgColor 91)
		(= fgColor (if (< xPlace 190) 215 else 192))
	)
	(if (not varColor)
		(Format @str {%d} [egoStats statNum])
	else
		(Format @str &rest)
	)
	(= theWidth (if varColor 60 else 22))
	(Display @str
		p_at xPlace yPlace
		p_mode teJustLeft
		p_font 123
		p_color fgColor
		p_width theWidth
	)
	(Display @str
		p_at (- xPlace 1) yPlace
		p_mode teJustLeft
		p_font 123
		p_color bgColor
		p_width theWidth
	)
)

(procedure (ShowSheet)
	(cast eachElementDo: #perform hideMe)
	(= oldCast cast)
	(= oldFeatures features)
	(= oldMouseDownHandler mouseDownHandler)
	(= oldKeyDownHandler keyDownHandler)
	(= mouseDownHandler NULL)
	(= keyDownHandler NULL)
	(= cast NULL)
	(= features NULL)
	((= cast newCast) add:)
	((= features newFeatures) add:)
	(myHero cel: heroType init:)
	((= mouseDownHandler newMH) add: myHero)
	((= keyDownHandler newKH) add: myHero)
	(DrawPic 904 PLAIN)
)

(procedure (HideSheet)
	(cast
		eachElementDo: #dispose
		eachElementDo: #delete
		release:
		dispose:
	)
	(features dispose:)
	(mouseDownHandler dispose:)
	(keyDownHandler dispose:)
	(DrawPic (curRoom picture?) PLAIN)
	(= cast oldCast)
	(= features oldFeatures)
	(= mouseDownHandler oldMouseDownHandler)
	(= keyDownHandler oldKeyDownHandler)
	(addToPics doit:)
	(cast eachElementDo: #perform showMe)
	(Bclr fHideCursor)
	(Bclr fCharSheetActive)
)

(instance newCast of EventHandler)

(instance newFeatures of EventHandler)

(instance newMH of EventHandler)

(instance newKH of EventHandler)

(instance hideMe of Code
	(method (doit obj)
		(obj z: (+ (obj z?) 1000))
	)
)

(instance showMe of Code	
	(method (doit obj)
		(obj z: (- (obj z?) 1000))
	)
)

(define LEFTVAL_X		175)
(define RIGHTVAL_X		288)
(define VAL_Y			40)
(define DERIV_X			200)	;Health, Stamina, Mana, and Experience
(define EXPER_Y			141)			
(define HEALTH_Y		153)
(define STAMINA_Y		165)
(define MANA_Y			177)

(instance theCharSheet of Code
	(method (doit &tmp [str 8])
		(= userInput (User input?))
		(= userControls (User controls?))
		(HandsOff)
		(= oldSortedFeatures useSortedFeatures)
		(ShowSheet)
		(Display @userName
			p_at 145 22
			p_color 215
			p_width 172
			p_mode teJustLeft
			p_font 123
		)
		(Display @userName
			p_at 144 22
			p_color 91
			p_width 175
			p_mode teJustLeft
			p_font 123
		)
		(ShowSkill LEFTVAL_X VAL_Y STR 0)
		(ShowSkill LEFTVAL_X (+ VAL_Y (* 12 1)) INT 0)
		(ShowSkill LEFTVAL_X (+ VAL_Y (* 12 2)) AGIL 0)
		(ShowSkill LEFTVAL_X (+ VAL_Y (* 12 3)) VIT 0)
		(ShowSkill LEFTVAL_X (+ VAL_Y (* 12 4)) LUCK 0)
		(ShowSkill LEFTVAL_X (+ VAL_Y (* 12 5)) MAGIC 0)
		(ShowSkill RIGHTVAL_X VAL_Y WEAPON 0)
		(ShowSkill RIGHTVAL_X (+ VAL_Y (* 12 1)) PARRY 0)
		(ShowSkill RIGHTVAL_X (+ VAL_Y (* 12 2)) DODGE 0)
		(ShowSkill RIGHTVAL_X (+ VAL_Y (* 12 3)) STEALTH 0)
		(ShowSkill RIGHTVAL_X (+ VAL_Y (* 12 4)) PICK 0)
		(ShowSkill RIGHTVAL_X (+ VAL_Y (* 12 5)) THROW 0)
		(ShowSkill RIGHTVAL_X (+ VAL_Y (* 12 6)) CLIMB 0)
		(ShowSkill DERIV_X EXPER_Y EXPER 1
			(Format @str {%d} [egoStats EXPER])
		)
		(ShowSkill DERIV_X HEALTH_Y HEALTH 1
			(Format @str
				{%d / %d}
				(/ (+ [egoStats HEALTH] 1) 2)
				(/ (+ (MaxHealth) 1) 2)
			)
		)
		(ShowSkill DERIV_X STAMINA_Y STAMINA 1
			(Format @str
				{%d / %d}
				(/ (+ [egoStats STAMINA] 3) 4)
				(/ (+ (MaxStamina) 3) 4)
			)
		)
		(ShowSkill DERIV_X MANA_Y MANA 1
			(Format @str
				{%d / %d}
				[egoStats MANA]
				(MaxMana)
			)
		)
	)
	
	(method (dispose &tmp i)
		(HideSheet)
		(= useSortedFeatures oldSortedFeatures)
		(for ((= i 0)) (< i NUM_ATTRIBS) ((++ i))
			(= [oldStats i] [egoStats i])
		)
		(= oldScore score)
		(HandsOn)
		(User canInput: userInput canControl: userControls)
		(super dispose:)
		(theGame setCursor: theCursor TRUE)
		(DisposeScript CHARSHEET)
	)
)

(instance myHero of View
	(properties
		x 51
		y 145
		view vCharSheet
	)
	
	(method (handleEvent event)
		(event claimed: TRUE)
		(if
			(or
				(and
					(== (event type?) keyDown)
					(OneOf (event message?) ENTER ESC)
				)
				(== (event type?) mouseDown)
			)
			(theCharSheet dispose:)
		)
	)
)
