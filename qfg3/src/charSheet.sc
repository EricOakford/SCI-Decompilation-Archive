;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARSHEET)
(include game.sh) (include "15.shm") (include "140.shm")
(use Main)
(use User)
(use Actor)
(use System)

(public
	charSheet 0
)

(local
	[local0 2] = [10000]
	oldSortedFeatures
	oldCast
	oldFeatures
	oldMDH
	oldKDH
	oldTime
	userCanControl
	oldCur
	[theBuf 12]
)
(procedure (ShowSkill xPlace yPlace statNum varColor &tmp bgColor fgColor theWidth [str 6])
	(if (!= [egoStats statNum] [oldStats statNum])
		(= bgColor 54)
		(= fgColor 231)
	else
		(= bgColor 82)
		(= fgColor 231)
	)
	(if (and (== statNum HEALTH) (Btst fPoisoned))
		(= bgColor 70)
		(= fgColor 10)
	)
	(if (not varColor)
		(Format @str {%d} [egoStats statNum])
	else
		(Format @str &rest)
	)
	(= theWidth (if varColor 60 else 22))
	(Display @str
		p_at xPlace yPlace
		p_mode teJustRight
		p_font 123
		p_color fgColor
		p_width theWidth
	)
	(Display @str
		p_at (- xPlace 1) yPlace
		p_mode teJustRight
		p_font 123
		p_color bgColor
		p_width theWidth
	)
)

(procedure (ShowPaladinStat xPlace yPlace varColor &tmp bgColor fgColor theWidth [str 6])
	(if
		(and
			paladinStat
			(!= [egoStats HONOR] [oldStats HONOR])
		)
		(= bgColor 54)
		(= fgColor 231)
	else
		(= bgColor 82)
		(= fgColor 231)
	)
	(if (not varColor)
		(Format @str 15 0
			(cond 
				(
					(<
						(if paladinStat
							(- [egoStats HONOR] paladinStat)
						)
						0
					)
					0
				)
				(paladinStat (- [egoStats HONOR] paladinStat))
			)
		)
	else
		(Format @str &rest)
	)
	(= theWidth (if varColor 60 else 22))
	(Display @str
		p_at xPlace yPlace
		p_mode teJustRight
		p_font 123
		p_color fgColor
		p_width theWidth
	)
	(Display @str
		p_at (- xPlace 1) yPlace
		p_mode teJustRight
		p_font 123
		p_color bgColor
		p_width theWidth
	)
)

(procedure (ShowSheet)
	(theIconBar disable:)
	(cast eachElementDo: #perform hideMe)
	(= oldCast cast)
	(= oldFeatures features)
	(= oldMDH mouseDownHandler)
	(= oldKDH keyDownHandler)
	(= mouseDownHandler
		(= keyDownHandler (= cast (= features 0)))
	)
	((= cast newCast) add:)
	((= features newFeatures) add:)
	(myHero loop:
		(switch origHeroType
			(FIGHTER 1)
			(THIEF 2)
			(MAGIC_USER 0)
			(PALADIN 1)
		)
		init:
	)
	((= mouseDownHandler newMH) add: myHero)
	((= keyDownHandler newKH) add: myHero)
	(DrawPic 145 PLAIN)
)

(procedure (HideSheet)
	((theIconBar curIcon?) cursor: oldCur)
	(cast
		eachElementDo: #dispose
		eachElementDo: #delete
		release:
		dispose:
	)
	(features dispose:)
	(mouseDownHandler dispose:)
	(keyDownHandler dispose:)
	(cond
		((== (curRoom style?) -1)
			(DrawPic (curRoom picture?) PLAIN)
		)
		((& (curRoom style?) HMIRROR)
			(DrawPic (curRoom picture?) (curRoom style?))
		)
		(else
			(DrawPic (curRoom picture?) PLAIN)
		)
	)
	(= cast oldCast)
	(= features oldFeatures)
	(= mouseDownHandler oldMDH)
	(= keyDownHandler oldKDH)
	(addToPics doit:)
	(cast eachElementDo: #perform showMe)
)

(procedure (IconBarOff &tmp i n)
	(= n -32768)
	(= i 0)
	(while (<= i 10)
		(if (& disabledIcons n)
			(theIconBar disable: i)
		)
		(= n (>> n $0001))
		(++ i)
	)
)

(instance newCast of EventHandler)

(instance newFeatures of EventHandler)

(instance newMH of EventHandler)

(instance newKH of EventHandler)

(instance hideMe of Code
	
	(method (doit param1)
		(param1 z: (+ (param1 z?) 1000))
	)
)

(instance showMe of Code
	
	(method (doit param1)
		(param1 z: (- (param1 z?) 1000))
	)
)

(instance charSheet of Code

	(method (doit &tmp [str 6] bgColor fgColor evt temp9)
		(= userCanControl (User canControl:))
		(HandsOff)
		(Bset fCharSheetActive)
		(= oldTime Clock)
		(= oldSortedFeatures useSortedFeatures)
		(ShowSheet)
		(Display @userName
			p_at 110 (if (> (StrLen @userName) 12) 7 else 3)
			p_color 231
			p_width 172
			p_mode teJustLeft
			p_font (if (> (StrLen @userName) 12) 3 else 123)
		)
		(Display @userName
			p_at 109 (if (> (StrLen @userName) 12) 7 else 3)
			p_color 82
			p_width 175
			p_mode teJustLeft
			p_font (if (> (StrLen @userName) 12) 3 else 123)
		)
		(switch heroType
			(FIGHTER
				(Message MsgGet CHARSEL N_FIGHTER NULL NULL 1 @theBuf)
				(Display @theBuf
					p_at 140 31
					p_font 123
					p_color teJustLeft
				)
				(Display @theBuf
					p_font 123
					p_at 139 30
					p_color 30
				)
			)
			(MAGIC_USER
				(Message MsgGet CHARSEL N_WIZARD NULL NULL 1 @theBuf)
				(Display @theBuf
					p_at 140 31
					p_font 123
					p_color teJustLeft
				)
				(Display @theBuf
					p_font 123
					p_at 139 30
					p_color 30
				)
			)
			(THIEF
				(Message MsgGet CHARSEL N_THIEF NULL NULL 1 @theBuf)
				(Display @theBuf
					p_at 145 31
					p_font 123
					p_color 0
				)
				(Display @theBuf
					p_font 123
					p_at 144 30
					p_color 30
				)
			)
			(PALADIN
				(Message MsgGet CHARSEL N_PALADIN NULL NULL 1 @theBuf)
				(Display @theBuf
					p_at 137 31
					p_font 123
					p_color 0
				)
				(Display @theBuf
					p_font 123
					p_at 136 30
					p_color 30
				)
			)
		)
		(ShowSkill 93 34 STR 0)
		(ShowSkill 93 50 INT 0)
		(ShowSkill 93 67 AGIL 0)
		(ShowSkill 93 83 VIT 0)
		(ShowSkill 93 99 LUCK 0)
		(ShowSkill 93 115 MAGIC 0)
		(ShowPaladinStat 93 130 0)
		(ShowSkill 286 35 WEAPON 0)
		(ShowSkill 286 48 PARRY 0)
		(ShowSkill 286 60 DODGE 0)
		(ShowSkill 286 72 STEALTH 0)
		(ShowSkill 286 84 PICK 0)
		(ShowSkill 286 96 THROW 0)
		(ShowSkill 286 108 CLIMB 0)
		(ShowSkill 286 120 COMM 0)
		(ShowSkill 286 132 HONOR 0)
		(ShowSkill 58 171 HEALTH 1
			(Format @str {%d/%d} [egoStats HEALTH] (ego maxHealth:))
		)
		(ShowSkill 250 157 STAMINA 1
			(Format @str {%d/%d} [egoStats STAMINA] (ego maxStamina:))
		)
		(ShowSkill 250 170 MANA 1
			(Format @str {%d/%d} [egoStats MANA] (ego maxMana:))
		)
		(Format @str {%d} score)
		(if (!= oldScore score)
			(= bgColor 54)
			(= fgColor 231)
		else
			(= bgColor 82)
			(= fgColor 231)
		)
		(Display @str
			p_at 98 156
			p_color fgColor
			p_width 60
			p_mode teJustLeft
			p_font 123
		)
		(Display @str
			p_at 97 156
			p_color bgColor
			p_width 60
			p_mode teJustLeft
			p_font 123
		)
		(User input: TRUE)
		(= oldCur ((theIconBar curIcon?) cursor?))
		((theIconBar curIcon?) cursor: ARROW_CURSOR)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(Animate (cast elements?) FALSE)
		(repeat
			(= temp9 ((= evt (Event new:)) type?))
			(evt dispose:)
			(breakif (OneOf temp9 keyDown mouseDown direction))
		)
		(self dispose:)
		(curRoom notify: -15)
	)
	
	(method (dispose &tmp i)
		(HideSheet)
		(= useSortedFeatures oldSortedFeatures)
		(= i 0)
		(while (< i OPEN)
			(= [oldStats i] [egoStats i])
			(++ i)
		)
		(= oldScore score)
		(if userCanControl
			(HandsOn)
		else
			(User canControl: FALSE)
			(HandsOn)
		)
		(IconBarOff)
		(super dispose:)
		(theGame setCursor: theCursor TRUE)
		(Bclr fCharSheetActive)
		(theIconBar enable:)
		(DisposeScript CHARSHEET)
	)
)

(instance myHero of View
	(properties
		x 162
		y 154
		view 140
	)
)
