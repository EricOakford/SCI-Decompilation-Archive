;;; Sierra Script 1.0 - (do not remove this comment)

;**
;**	MENU.SH
;**

;** Icon bar 
(define	ICONBAR_X				0)
(define	ICONBAR_Y				0)
(define	ICONBAR_H				27)
(define	ICONBAR_W				320)
;(define	ICONBAR_BTM				(- (- ICONBAR_H ICONBAR_Y) 1))
(define	PIC_OFFSET				10)
(define	BAR_COLOR				5)
(define	BORDER_COLOR			19)
(define	SHADOW_COLOR			0)
(define	SELECTOR_BOX_COLOR	12)

(define	SELECTED					$0002)
(define	INV_ENABLED				$0004)
(define	HIDE_CURSOR				$0010)	;Don't draw cursor on screen

;** Inventory window
(define	INV_MAX_W				295)
(define	INV_MAX_H				170)
(define	INV_LEFT					47)
(define	INV_TOP					19)
(define	INV_RIGHT				273)
(define	INV_BOTTOM				188)
(define	INV_HORZ_SEG_Y			173)
(define	INV_VERT_SEG_X			255)
(define	INV_VERT_SEG_Y			34)
(define	INV_COLOR				5); WINDOW_BACK)
(define	INV_SEL_BOX_COLOR		12); WINDOW_COLOR)
(define	INV_TEXT_COLOR			17)
(define	INV_ICON_LEFT			48)
(define	INV_ICON_TOP			20)

;(define	MASK_VIEW				vIcons)
;(define	MASK_LOOP				lUserIcons)
;(define	MASK_CEL					cDimMask)

(enum
	NONE
	UP
	UP_RIGHT
	RIGHT
	DOWN_RIGHT
	DOWN
	DOWN_LEFT
	LEFT
	UP_LEFT
)

(define	FIVE_KEY					$4C00)
(define	INS_KEY					$5200)
(define	DEL_KEY					$5300)
