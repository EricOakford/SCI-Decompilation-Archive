;;; Sierra Script 1.0 - (do not remove this comment)
; Icons
(enum 
	ICON_LEFT	;left border of icon bar
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_ACTIONS
	ICON_CAST
	ICON_USEIT
	ICON_INVENTORY
	ICON_CONTROL
	ICON_HELP
	ICON_RIGHT	;right border of icon bar
)

; Action Bar Icons
(define ACTION_WALK		$0001)
(define ACTION_RUN		$0002)
(define ACTION_SNEAK	$0004)
(define ACTION_REST		$0008)

; Spell icons (used for spell menu)
(define SPELL_OPEN		$0001)
(define SPELL_DETECT	$0002)
(define SPELL_TRIGGER	$0004)
(define SPELL_DAZZLE	$0008)
(define SPELL_ZAP		$0010)
(define SPELL_CALM		$0020)
(define SPELL_FLAMEDART	$0040)
(define SPELL_FETCH		$0080)
;disabled spells (these are never set)
(define DISABLED_OPEN		$0100)
(define DISABLED_DETECT		$0200)
(define DISABLED_TRIGGER	$0400)
(define DISABLED_DAZZLE		$0800)
(define DISABLED_ZAP		$1000)
(define DISABLED_CALM		$2000)
(define DISABLED_FLAMEDART	$4000)
(define DISABLED_FETCH		$8000)