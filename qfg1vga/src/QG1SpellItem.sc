;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPELLS)
(include game.sh) (include "558.shm")
(use Main)
(use GloryWindow)
(use Procs)
(use Print)
(use IconBar)
(use Invent)
(use System)

(public
	glorySpells 0
)

(procedure (NotUsefulHere)
	(messager say: N_NOTUSEFULHERE NULL NULL 1 0 SPELLS)
)

(class QG1SpellItem of InvItem
	(properties
		view vSpellIcons
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		modNum SPELLS
		amount 0
		cost 0
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(self doVerb: V_DO)
				(return FALSE)
			else
				FALSE
			)
		)
	)
	
	(method (doVerb theVerb &tmp [spellBuf 10] [descBuf 60] [str 60])
		(if (== theVerb V_LOOK)
			(Message MsgGet SPELLS noun NULL NULL 1 @spellBuf)
			(Message MsgGet SPELLS N_SPELL NULL NULL 1 @descBuf)
			(Print
				font: userFont
				mode: teJustCenter
				width: (if (== noun N_FLAMEDART) 200 else 180)
				addTextF: @str @descBuf @spellBuf amount cost
				init:
			)
		)
		(super doVerb: theVerb)
	)
)

(instance glorySpells of Inventory
	(properties
		normalHeading 1019
		empty 1037
	)
	
	(method (init)
		(if (& spellMask SPELL_OPEN)
			(openSpell
				owner: ego
				amount: [egoStats OPEN]
				cost: spellCost	;spCostOpen
			)
		)
		(if (& spellMask SPELL_DETECT)
			(detectMagicSpell
				owner: ego
				amount: [egoStats DETMAGIC]
				cost: spCostDetect
			)
		)
		(if (& spellMask SPELL_TRIGGER)
			(triggerSpell
				owner: ego
				amount: [egoStats TRIGGER]
				cost: spCostTrigger
			)
		)
		(if (& spellMask SPELL_DAZZLE)
			(dazzleSpell
				owner: ego
				amount: [egoStats DAZZLE]
				cost: spCostDazzle
			)
		)
		(if (& spellMask SPELL_ZAP)
			(zapSpell
				owner: ego
				amount: [egoStats ZAP]
				cost: spCostZap
			)
		)
		(if (& spellMask SPELL_CALM)
			(calmSpell
				owner: ego
				amount: [egoStats CALM]
				cost: spCostCalm
			)
		)
		(if (& spellMask SPELL_FLAMEDART)
			(flameDartSpell
				owner: ego
				amount: [egoStats FLAMEDART]
				cost: spCostFlame
			)
		)
		(if (& spellMask SPELL_FETCH)
			(fetchSpell
				owner: ego
				amount: [egoStats FETCH]
				cost: spCostFetch
			)
		)
		(self
			window: spellWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
			add:
				openSpell
				detectMagicSpell
				triggerSpell
				dazzleSpell
				zapSpell
				calmSpell
				flameDartSpell
				fetchSpell
				invLook
				invSelect
				ok
				invHelp
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			eachElementDo: #init
			state: NOCLICKHELP
		)
	)
	
	(method (showSelf &tmp temp0)
		(super showSelf:)
		(self dispose: release:)
		(DisposeScript SPELLS)
	)
)

(instance spellWin of GloryWindow
	(properties
		yOffset 28
	)
	
	(method (open)
		(invLook
			nsLeft: (- (/ (- (self right?) (self left?)) 2) 60)
		)
		(super open:)
	)
)

(instance openSpell of QG1SpellItem
	(properties
		noun N_OPEN
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(if (== theVerb V_DO)
				(glorySpells hide:)
				(cond 
					((& spellMask DISABLED_OPEN)
						(NotUsefulHere)
					)
					((not (CastSpell OPEN)))
					(
						(OneOf curRoomNum
							10 13 15 16 29 30 31 41 65 67 73
							76 82 83 84 91 93 94 96 97 141
							;EO: additional rooms to restore messages
							rGraveyard
						)
						((= evt (Event new:))
							type: mouseDown
							message: V_OPEN
						)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(return TRUE)
					)
					(else
						(VerbSpell V_OPEN)
					)
				)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance detectMagicSpell of QG1SpellItem
	(properties
		loop 1
		noun N_DETECT
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(if (== theVerb V_DO)
				(glorySpells hide:)
				(cond 
					((& spellMask DISABLED_DETECT)
						(NotUsefulHere)
					)
					((not
						(CastSpell DETMAGIC))
					)
					(
						(OneOf curRoomNum
							10 13 14 15 22 29 30 31 55 58 65 67 73
							76 77 78 82 83 84 91 93 94 96 97 141
							;EO: additional rooms to restore messages
							rGraveyard
						)
						((= evt (Event new:))
							type: mouseDown
							message: V_DETECT
						)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(return TRUE)
					)
					(else
						(VerbSpell V_DETECT)
					)
				)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance triggerSpell of QG1SpellItem
	(properties
		loop 2
		noun N_TRIGGER
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(if (== theVerb V_DO)
				(glorySpells hide:)
				(cond 
					((& spellMask DISABLED_TRIGGER)
						(NotUsefulHere)
					)
					((not
						(CastSpell TRIGGER))
					)
					(
						(OneOf curRoomNum
							13 58 15 29 30 31 65 73
							82 83 91 96 97 141
							;EO: additional rooms to restore messages
							rGraveyard
						)
						((= evt (Event new:))
							type: mouseDown
							message: V_TRIGGER
						)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(return TRUE)
					)
					(else
						(VerbSpell V_TRIGGER)
					)
				)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance dazzleSpell of QG1SpellItem
	(properties
		loop 4
		noun N_DAZZLE
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(if (== theVerb V_DO)
				(glorySpells hide:)
				(cond 
					((& spellMask DISABLED_DAZZLE)
						(NotUsefulHere)
					)
					((not
						(CastSpell DAZZLE))
					)
					(
						(OneOf curRoomNum
							13 14 15 16 29 30 31 38 40 54 55 58
							65 67 73 76 77 78 82 83 84 91 93 94
							96 97 141 11 12 17 18 19 23 24 25 26
							27 33 34 35 36 42 43 44 51 52 56 57
							61 62 63 69 71 72 74 75 79 80 81 85
							86 92
							;EO: additional rooms to restore messages
							rGraveyard
						)
						((= evt (Event new:))
							type: mouseDown
							message: V_DAZZLE
						)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(return TRUE)
					)
					(else
						(VerbSpell V_DAZZLE)
					)
				)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance zapSpell of QG1SpellItem
	(properties
		loop 3
		noun N_ZAP
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(if (== theVerb V_DO)
				(glorySpells hide:)
				(cond 
					((& spellMask DISABLED_ZAP)
						(NotUsefulHere)
					)
					((not (CastSpell ZAP)))
					(
						(OneOf curRoomNum
							13 29 30 31 65 91 96 97 141 11 12 17 18
							19 23 24 25 26 27 33 34 35 36 42 43 44
							51 52 56 57 61 62 63 69 71 72 74 75 79 80
							81 85 86 92
						)
						((= evt (Event new:))
							type: mouseDown
							message: V_ZAP
						)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(return TRUE)
					)
					(else
						(VerbSpell V_ZAP)
					)
				)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance calmSpell of QG1SpellItem
	(properties
		loop 5
		noun N_CALM
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(if (== theVerb V_DO)
				(glorySpells hide:)
				(cond 
					((& spellMask DISABLED_CALM)
						(NotUsefulHere)
					)
					((not (CastSpell CALM)))
					(
						(OneOf curRoomNum
							10 13 14 15 29 30 31 58 65 67 73 84 91
							93 94 96 97 141 11 12 17 18 19 23 24 25
							26 27 33 34 35 36 42 43 44 51 52 56 57
							61 62 63 69 71 72 74 75 79 80 81 85 86 92
							;EO: additional rooms to restore messages
							rGraveyard
						)
						((= evt (Event new:))
							type: mouseDown
							message: V_CALM
						)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(return TRUE)
					)
					(else
						(VerbSpell V_CALM)
					)
				)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance flameDartSpell of QG1SpellItem
	(properties
		loop 6
		cursor vCastCursor
		noun N_FLAMEDART
	)
	
	(method (doVerb theVerb &tmp evt)
		(if (== theVerb V_DO)
			(glorySpells hide:)
			(cond 
				((& spellMask DISABLED_FLAMEDART)
					(NotUsefulHere)
				)
				((not (CastSpell FLAMEDART)))
				(else
					((theIconBar at: ICON_CAST)
						message: V_FLAME
						cursor: vCastCursor
					)
					(theIconBar curIcon: (theIconBar at: ICON_CAST))
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fetchSpell of QG1SpellItem
	(properties
		loop 7
		noun N_FETCH
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(if (== theVerb V_DO)
				(glorySpells hide:)
				(cond 
					((& spellMask DISABLED_FETCH)
						(NotUsefulHere)
					)
					((not (CastSpell FETCH)))
					(
						(OneOf curRoomNum
							13 15 16 29 30 31 54 65 73 91 96 97 141
							;EO: additional rooms to restore messages
							rGraveyard
						)
						((= evt (Event new:))
							type: mouseDown
							message: V_FETCH
						)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(return TRUE)
					)
					(else
						(VerbSpell V_FETCH)
					)
				)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance invLook of IconItem
	(properties
		view vInvIcons
		loop 2
		cel 0
		cursor vLookCursor
		message V_LOOK
		signal (| FIXED_POSN RELVERIFY)
		noun N_LOOK
		modNum SPELLS
		helpVerb V_HELP
	)
)

(instance invSelect of IconItem
	(properties
		view vInvIcons
		loop 0
		cel 0
		cursor vDoCursor
		noun N_SELECT
		modNum SPELLS
		helpVerb V_HELP
	)
)

(instance invHelp of IconItem
	(properties
		view vInvIcons
		loop 1
		cel 0
		cursor vHelpCursor
		message V_HELP
		noun N_HELP
		modNum SPELLS
		helpVerb V_HELP
	)
	
	(method (show)
		(super show:)
		(DrawCel vInvIcons 7 0
			(+ nsLeft (CelWide view loop cel))
			nsTop
			-1
		)
	)
)

(instance ok of IconItem
	(properties
		view vInvIcons
		loop 3
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		noun N_OK
		modNum SPELLS
		helpVerb V_HELP
	)
)
