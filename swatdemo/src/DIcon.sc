;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DICON.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Bob Heitman
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The dialog icon class.
;;;;
;;;;	Classes:
;;;;		DIcon


(script# DICON)
(include game.sh)
(use DItem)


(class DIcon 	kindof DItem
	;;; Icons are simply a view/loop/cel combination created by the view
	;;; editor VE.  They are generally not selectable.

	(properties
		type		dIcon
	)

	(method (setSize &tmp w h)
		(= nsLeft x)
		(= nsTop y)
		(= nsRight (- (+ nsLeft (CelWide view loop cel)) 1))
		(= nsBottom (- (+ nsTop (CelHigh view loop cel)) 1))
	)
)